/*      */ package org.hibernate.impl;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InvalidObjectException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.ObjectStreamException;
/*      */ import java.io.PrintStream;
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.sql.Connection;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Properties;
/*      */ import java.util.Set;
/*      */ import javax.naming.NamingException;
/*      */ import javax.naming.Reference;
/*      */ import javax.naming.StringRefAddr;
/*      */ import javax.transaction.TransactionManager;
/*      */ import org.hibernate.AssertionFailure;
/*      */ import org.hibernate.ConnectionReleaseMode;
/*      */ import org.hibernate.EntityMode;
/*      */ import org.hibernate.HibernateException;
/*      */ import org.hibernate.Interceptor;
/*      */ import org.hibernate.MappingException;
/*      */ import org.hibernate.ObjectNotFoundException;
/*      */ import org.hibernate.QueryException;
/*      */ import org.hibernate.SessionFactory;
/*      */ import org.hibernate.SessionFactoryObserver;
/*      */ import org.hibernate.StatelessSession;
/*      */ import org.hibernate.cache.CacheKey;
/*      */ import org.hibernate.cache.CollectionRegion;
/*      */ import org.hibernate.cache.EntityRegion;
/*      */ import org.hibernate.cache.QueryCache;
/*      */ import org.hibernate.cache.QueryCacheFactory;
/*      */ import org.hibernate.cache.QueryResultsRegion;
/*      */ import org.hibernate.cache.Region;
/*      */ import org.hibernate.cache.RegionFactory;
/*      */ import org.hibernate.cache.TimestampsRegion;
/*      */ import org.hibernate.cache.UpdateTimestampsCache;
/*      */ import org.hibernate.cache.access.AccessType;
/*      */ import org.hibernate.cache.access.CollectionRegionAccessStrategy;
/*      */ import org.hibernate.cache.access.EntityRegionAccessStrategy;
/*      */ import org.hibernate.cache.impl.CacheDataDescriptionImpl;
/*      */ import org.hibernate.cfg.Configuration;
/*      */ import org.hibernate.cfg.Settings;
/*      */ import org.hibernate.classic.Session;
/*      */ import org.hibernate.connection.ConnectionProvider;
/*      */ import org.hibernate.context.CurrentSessionContext;
/*      */ import org.hibernate.context.JTASessionContext;
/*      */ import org.hibernate.context.ManagedSessionContext;
/*      */ import org.hibernate.context.ThreadLocalSessionContext;
/*      */ import org.hibernate.dialect.Dialect;
/*      */ import org.hibernate.dialect.function.SQLFunctionRegistry;
/*      */ import org.hibernate.engine.FilterDefinition;
/*      */ import org.hibernate.engine.Mapping;
/*      */ import org.hibernate.engine.NamedQueryDefinition;
/*      */ import org.hibernate.engine.NamedSQLQueryDefinition;
/*      */ import org.hibernate.engine.ResultSetMappingDefinition;
/*      */ import org.hibernate.engine.SessionFactoryImplementor;
/*      */ import org.hibernate.engine.query.HQLQueryPlan;
/*      */ import org.hibernate.engine.query.QueryPlanCache;
/*      */ import org.hibernate.engine.query.ReturnMetadata;
/*      */ import org.hibernate.engine.query.sql.NativeSQLQuerySpecification;
/*      */ import org.hibernate.event.EventListeners;
/*      */ import org.hibernate.exception.SQLExceptionConverter;
/*      */ import org.hibernate.id.IdentifierGenerator;
/*      */ import org.hibernate.id.UUIDHexGenerator;
/*      */ import org.hibernate.jdbc.BatcherFactory;
/*      */ import org.hibernate.mapping.KeyValue;
/*      */ import org.hibernate.mapping.PersistentClass;
/*      */ import org.hibernate.mapping.RootClass;
/*      */ import org.hibernate.metadata.ClassMetadata;
/*      */ import org.hibernate.metadata.CollectionMetadata;
/*      */ import org.hibernate.persister.PersisterFactory;
/*      */ import org.hibernate.persister.collection.CollectionPersister;
/*      */ import org.hibernate.persister.entity.EntityPersister;
/*      */ import org.hibernate.persister.entity.Queryable;
/*      */ import org.hibernate.pretty.MessageHelper;
/*      */ import org.hibernate.proxy.EntityNotFoundDelegate;
/*      */ import org.hibernate.stat.Statistics;
/*      */ import org.hibernate.stat.StatisticsImpl;
/*      */ import org.hibernate.stat.StatisticsImplementor;
/*      */ import org.hibernate.tool.hbm2ddl.SchemaExport;
/*      */ import org.hibernate.tool.hbm2ddl.SchemaUpdate;
/*      */ import org.hibernate.tool.hbm2ddl.SchemaValidator;
/*      */ import org.hibernate.transaction.TransactionFactory;
/*      */ import org.hibernate.transaction.TransactionManagerLookup;
/*      */ import org.hibernate.type.AssociationType;
/*      */ import org.hibernate.type.Type;
/*      */ import org.hibernate.util.CollectionHelper;
/*      */ import org.hibernate.util.ReflectHelper;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ 
/*      */ public final class SessionFactoryImpl
/*      */   implements SessionFactory, SessionFactoryImplementor
/*      */ {
/*  144 */   private static final Logger log = LoggerFactory.getLogger(SessionFactoryImpl.class);
/*  145 */   private static final IdentifierGenerator UUID_GENERATOR = new UUIDHexGenerator();
/*      */   private final String name;
/*      */   private final String uuid;
/*      */   private final transient Map entityPersisters;
/*      */   private final transient Map classMetadata;
/*      */   private final transient Map collectionPersisters;
/*      */   private final transient Map collectionMetadata;
/*      */   private final transient Map collectionRolesByEntityParticipant;
/*      */   private final transient Map identifierGenerators;
/*      */   private final transient Map namedQueries;
/*      */   private final transient Map namedSqlQueries;
/*      */   private final transient Map sqlResultSetMappings;
/*      */   private final transient Map filters;
/*      */   private final transient Map imports;
/*      */   private final transient Interceptor interceptor;
/*      */   private final transient Settings settings;
/*      */   private final transient Properties properties;
/*      */   private transient SchemaExport schemaExport;
/*      */   private final transient TransactionManager transactionManager;
/*      */   private final transient QueryCache queryCache;
/*      */   private final transient UpdateTimestampsCache updateTimestampsCache;
/*      */   private final transient Map queryCaches;
/*  169 */   private final transient Map allCacheRegions = new HashMap();
/*  170 */   private final transient StatisticsImpl statistics = new StatisticsImpl(this);
/*      */   private final transient EventListeners eventListeners;
/*      */   private final transient CurrentSessionContext currentSessionContext;
/*      */   private final transient EntityNotFoundDelegate entityNotFoundDelegate;
/*      */   private final transient SQLFunctionRegistry sqlFunctionRegistry;
/*      */   private final transient SessionFactoryObserver observer;
/*  177 */   private QueryPlanCache queryPlanCache = new QueryPlanCache(this);
/*      */ 
/*  179 */   private transient boolean isClosed = false;
/*      */   private Configuration configuration;
/*      */ 
/*      */   public SessionFactoryImpl(Configuration cfg, Mapping mapping, Settings settings, EventListeners listeners, SessionFactoryObserver observer)
/*      */     throws HibernateException
/*      */   {
/*  190 */     log.info("building session factory");
/*      */ 
/*  192 */     this.configuration = cfg;
/*      */ 
/*  194 */     this.properties = new Properties();
/*  195 */     this.properties.putAll(cfg.getProperties());
/*  196 */     this.interceptor = cfg.getInterceptor();
/*  197 */     this.settings = settings;
/*  198 */     this.sqlFunctionRegistry = new SQLFunctionRegistry(settings.getDialect(), cfg.getSqlFunctions());
/*  199 */     this.eventListeners = listeners;
/*  200 */     this.observer = (observer != null ? observer : new SessionFactoryObserver()
/*      */     {
/*      */       public void sessionFactoryCreated(SessionFactory factory)
/*      */       {
/*      */       }
/*      */ 
/*      */       public void sessionFactoryClosed(SessionFactory factory)
/*      */       {
/*      */       }
/*      */     });
/*  206 */     this.filters = new HashMap();
/*  207 */     this.filters.putAll(cfg.getFilterDefinitions());
/*      */ 
/*  209 */     if (log.isDebugEnabled()) {
/*  210 */       log.debug("Session factory constructed with filter configurations : " + this.filters);
/*      */     }
/*      */ 
/*  213 */     if (log.isDebugEnabled()) {
/*  214 */       log.debug(
/*  215 */         "instantiating session factory with properties: " + this.properties);
/*      */     }
/*      */ 
/*  220 */     settings.getRegionFactory().start(settings, this.properties);
/*      */ 
/*  224 */     this.identifierGenerators = new HashMap();
/*  225 */     Iterator classes = cfg.getClassMappings();
/*  226 */     while (classes.hasNext()) {
/*  227 */       PersistentClass model = (PersistentClass)classes.next();
/*  228 */       if (!model.isInherited()) {
/*  229 */         IdentifierGenerator generator = model.getIdentifier().createIdentifierGenerator(
/*  230 */           settings.getDialect(), 
/*  231 */           settings.getDefaultCatalogName(), 
/*  232 */           settings.getDefaultSchemaName(), 
/*  233 */           (RootClass)model);
/*      */ 
/*  235 */         this.identifierGenerators.put(model.getEntityName(), generator);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  244 */     String cacheRegionPrefix = settings.getCacheRegionPrefix() + ".";
/*      */ 
/*  246 */     this.entityPersisters = new HashMap();
/*  247 */     Map entityAccessStrategies = new HashMap();
/*  248 */     Map classMeta = new HashMap();
/*  249 */     classes = cfg.getClassMappings();
/*  250 */     while (classes.hasNext()) {
/*  251 */       PersistentClass model = (PersistentClass)classes.next();
/*  252 */       model.prepareTemporaryTables(mapping, settings.getDialect());
/*  253 */       String cacheRegionName = cacheRegionPrefix + model.getRootClass().getCacheRegionName();
/*      */ 
/*  255 */       EntityRegionAccessStrategy accessStrategy = (EntityRegionAccessStrategy)entityAccessStrategies.get(cacheRegionName);
/*  256 */       if ((accessStrategy == null) && (settings.isSecondLevelCacheEnabled())) {
/*  257 */         AccessType accessType = AccessType.parse(model.getCacheConcurrencyStrategy());
/*  258 */         if (accessType != null) {
/*  259 */           log.trace("Building cache for entity data [" + model.getEntityName() + "]");
/*  260 */           EntityRegion entityRegion = settings.getRegionFactory().buildEntityRegion(cacheRegionName, this.properties, CacheDataDescriptionImpl.decode(model));
/*  261 */           accessStrategy = entityRegion.buildAccessStrategy(accessType);
/*  262 */           entityAccessStrategies.put(cacheRegionName, accessStrategy);
/*  263 */           this.allCacheRegions.put(cacheRegionName, entityRegion);
/*      */         }
/*      */       }
/*  266 */       EntityPersister cp = PersisterFactory.createClassPersister(model, accessStrategy, this, mapping);
/*  267 */       this.entityPersisters.put(model.getEntityName(), cp);
/*  268 */       classMeta.put(model.getEntityName(), cp.getClassMetadata());
/*      */     }
/*      */ 
/*  274 */     this.classMetadata = classMeta;
/*      */ 
/*  277 */     Map tmpEntityToCollectionRoleMap = new HashMap();
/*  278 */     this.collectionPersisters = new HashMap();
/*  279 */     Iterator collections = cfg.getCollectionMappings();
/*  280 */     while (collections.hasNext()) {
/*  281 */       org.hibernate.mapping.Collection model = (org.hibernate.mapping.Collection)collections.next();
/*  282 */       String cacheRegionName = cacheRegionPrefix + model.getCacheRegionName();
/*  283 */       AccessType accessType = AccessType.parse(model.getCacheConcurrencyStrategy());
/*  284 */       CollectionRegionAccessStrategy accessStrategy = null;
/*  285 */       if ((accessType != null) && (settings.isSecondLevelCacheEnabled())) {
/*  286 */         log.trace("Building cache for collection data [" + model.getRole() + "]");
/*  287 */         CollectionRegion collectionRegion = settings.getRegionFactory().buildCollectionRegion(cacheRegionName, this.properties, CacheDataDescriptionImpl.decode(model));
/*  288 */         accessStrategy = collectionRegion.buildAccessStrategy(accessType);
/*  289 */         entityAccessStrategies.put(cacheRegionName, accessStrategy);
/*  290 */         this.allCacheRegions.put(cacheRegionName, collectionRegion);
/*      */       }
/*  292 */       CollectionPersister persister = PersisterFactory.createCollectionPersister(cfg, model, accessStrategy, this);
/*  293 */       this.collectionPersisters.put(model.getRole(), persister.getCollectionMetadata());
/*  294 */       Type indexType = persister.getIndexType();
/*  295 */       if ((indexType != null) && (indexType.isAssociationType()) && (!indexType.isAnyType())) {
/*  296 */         String entityName = ((AssociationType)indexType).getAssociatedEntityName(this);
/*  297 */         Set roles = (Set)tmpEntityToCollectionRoleMap.get(entityName);
/*  298 */         if (roles == null) {
/*  299 */           roles = new HashSet();
/*  300 */           tmpEntityToCollectionRoleMap.put(entityName, roles);
/*      */         }
/*  302 */         roles.add(persister.getRole());
/*      */       }
/*  304 */       Type elementType = persister.getElementType();
/*  305 */       if ((elementType.isAssociationType()) && (!elementType.isAnyType())) {
/*  306 */         String entityName = ((AssociationType)elementType).getAssociatedEntityName(this);
/*  307 */         Set roles = (Set)tmpEntityToCollectionRoleMap.get(entityName);
/*  308 */         if (roles == null) {
/*  309 */           roles = new HashSet();
/*  310 */           tmpEntityToCollectionRoleMap.put(entityName, roles);
/*      */         }
/*  312 */         roles.add(persister.getRole());
/*      */       }
/*      */     }
/*  315 */     this.collectionMetadata = Collections.unmodifiableMap(this.collectionPersisters);
/*  316 */     Iterator itr = tmpEntityToCollectionRoleMap.entrySet().iterator();
/*  317 */     while (itr.hasNext()) {
/*  318 */       Map.Entry entry = (Map.Entry)itr.next();
/*  319 */       entry.setValue(Collections.unmodifiableSet((Set)entry.getValue()));
/*      */     }
/*      */ 
/*  324 */     this.collectionRolesByEntityParticipant = tmpEntityToCollectionRoleMap;
/*      */ 
/*  327 */     this.namedQueries = new HashMap(cfg.getNamedQueries());
/*  328 */     this.namedSqlQueries = new HashMap(cfg.getNamedSQLQueries());
/*  329 */     this.sqlResultSetMappings = new HashMap(cfg.getSqlResultSetMappings());
/*  330 */     this.imports = new HashMap(cfg.getImports());
/*      */ 
/*  333 */     Iterator iter = this.entityPersisters.values().iterator();
/*  334 */     while (iter.hasNext()) {
/*  335 */       ((EntityPersister)iter.next()).postInstantiate();
/*      */     }
/*  337 */     iter = this.collectionPersisters.values().iterator();
/*  338 */     while (iter.hasNext()) {
/*  339 */       ((CollectionPersister)iter.next()).postInstantiate();
/*      */     }
/*      */ 
/*  344 */     this.name = settings.getSessionFactoryName();
/*      */     try {
/*  346 */       this.uuid = ((String)UUID_GENERATOR.generate(null, null));
/*      */     }
/*      */     catch (Exception e) {
/*  349 */       throw new AssertionFailure("Could not generate UUID");
/*      */     }
/*  351 */     SessionFactoryObjectFactory.addInstance(this.uuid, this.name, this, this.properties);
/*      */ 
/*  353 */     log.debug("instantiated session factory");
/*      */ 
/*  355 */     if (settings.isAutoCreateSchema()) {
/*  356 */       new SchemaExport(cfg, settings).create(false, true);
/*      */     }
/*  358 */     if (settings.isAutoUpdateSchema()) {
/*  359 */       new SchemaUpdate(cfg, settings).execute(false, true);
/*      */     }
/*  361 */     if (settings.isAutoValidateSchema()) {
/*  362 */       new SchemaValidator(cfg, settings).validate();
/*      */     }
/*  364 */     if (settings.isAutoDropSchema()) {
/*  365 */       this.schemaExport = new SchemaExport(cfg, settings);
/*      */     }
/*      */ 
/*  368 */     if (settings.getTransactionManagerLookup() != null) {
/*  369 */       log.debug("obtaining JTA TransactionManager");
/*  370 */       this.transactionManager = settings.getTransactionManagerLookup().getTransactionManager(this.properties);
/*      */     }
/*      */     else {
/*  373 */       if (settings.getTransactionFactory().isTransactionManagerRequired()) {
/*  374 */         throw new HibernateException("The chosen transaction strategy requires access to the JTA TransactionManager");
/*      */       }
/*  376 */       this.transactionManager = null;
/*      */     }
/*      */ 
/*  379 */     this.currentSessionContext = buildCurrentSessionContext();
/*      */ 
/*  381 */     if (settings.isQueryCacheEnabled()) {
/*  382 */       this.updateTimestampsCache = new UpdateTimestampsCache(settings, this.properties);
/*  383 */       this.queryCache = settings.getQueryCacheFactory()
/*  384 */         .getQueryCache(null, this.updateTimestampsCache, settings, this.properties);
/*  385 */       this.queryCaches = new HashMap();
/*  386 */       this.allCacheRegions.put(this.updateTimestampsCache.getRegion().getName(), this.updateTimestampsCache.getRegion());
/*  387 */       this.allCacheRegions.put(this.queryCache.getRegion().getName(), this.queryCache.getRegion());
/*      */     }
/*      */     else {
/*  390 */       this.updateTimestampsCache = null;
/*  391 */       this.queryCache = null;
/*  392 */       this.queryCaches = null;
/*      */     }
/*      */ 
/*  396 */     if (settings.isNamedQueryStartupCheckingEnabled()) {
/*  397 */       Map errors = checkNamedQueries();
/*  398 */       if (!errors.isEmpty()) {
/*  399 */         Set keys = errors.keySet();
/*  400 */         StringBuffer failingQueries = new StringBuffer("Errors in named queries: ");
/*  401 */         for (Iterator iterator = keys.iterator(); iterator.hasNext(); ) {
/*  402 */           String queryName = (String)iterator.next();
/*  403 */           HibernateException e = (HibernateException)errors.get(queryName);
/*  404 */           failingQueries.append(queryName);
/*  405 */           if (iterator.hasNext()) {
/*  406 */             failingQueries.append(", ");
/*      */           }
/*  408 */           log.error("Error in named query: " + queryName, e);
/*      */         }
/*  410 */         throw new HibernateException(failingQueries.toString());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  415 */     getStatistics().setStatisticsEnabled(settings.isStatisticsEnabled());
/*      */ 
/*  418 */     EntityNotFoundDelegate entityNotFoundDelegate = cfg.getEntityNotFoundDelegate();
/*  419 */     if (entityNotFoundDelegate == null)
/*  420 */       entityNotFoundDelegate = new EntityNotFoundDelegate() {
/*      */         public void handleEntityNotFound(String entityName, Serializable id) {
/*  422 */           throw new ObjectNotFoundException(id, entityName);
/*      */         }
/*      */       };
/*  426 */     this.entityNotFoundDelegate = entityNotFoundDelegate;
/*      */ 
/*  428 */     this.observer.sessionFactoryCreated(this);
/*      */   }
/*      */ 
/*      */   public QueryPlanCache getQueryPlanCache()
/*      */   {
/*  433 */     return this.queryPlanCache;
/*      */   }
/*      */ 
/*      */   private Map checkNamedQueries() throws HibernateException {
/*  437 */     Map errors = new HashMap();
/*      */ 
/*  439 */     log.debug("Checking " + this.namedQueries.size() + " named HQL queries");
/*  440 */     Iterator itr = this.namedQueries.entrySet().iterator();
/*  441 */     while (itr.hasNext()) {
/*  442 */       Map.Entry entry = (Map.Entry)itr.next();
/*  443 */       String queryName = (String)entry.getKey();
/*  444 */       NamedQueryDefinition qd = (NamedQueryDefinition)entry.getValue();
/*      */       try
/*      */       {
/*  447 */         log.debug("Checking named query: " + queryName);
/*      */ 
/*  449 */         this.queryPlanCache.getHQLQueryPlan(qd.getQueryString(), false, CollectionHelper.EMPTY_MAP);
/*      */       }
/*      */       catch (QueryException e) {
/*  452 */         errors.put(queryName, e);
/*      */       }
/*      */       catch (MappingException e) {
/*  455 */         errors.put(queryName, e);
/*      */       }
/*      */     }
/*      */ 
/*  459 */     log.debug("Checking " + this.namedSqlQueries.size() + " named SQL queries");
/*  460 */     itr = this.namedSqlQueries.entrySet().iterator();
/*  461 */     while (itr.hasNext()) {
/*  462 */       Map.Entry entry = (Map.Entry)itr.next();
/*  463 */       String queryName = (String)entry.getKey();
/*  464 */       NamedSQLQueryDefinition qd = (NamedSQLQueryDefinition)entry.getValue();
/*      */       try
/*      */       {
/*  467 */         log.debug("Checking named SQL query: " + queryName);
/*      */         NativeSQLQuerySpecification spec;
///*      */         NativeSQLQuerySpecification spec;
/*  471 */         if (qd.getResultSetRef() != null) {
/*  472 */           ResultSetMappingDefinition definition = (ResultSetMappingDefinition)this.sqlResultSetMappings.get(qd.getResultSetRef());
/*  473 */           if (definition == null) {
/*  474 */             throw new MappingException("Unable to find resultset-ref definition: " + qd.getResultSetRef());
/*      */           }
/*  476 */           spec = new NativeSQLQuerySpecification(
/*  477 */             qd.getQueryString(), 
/*  478 */             definition.getQueryReturns(), 
/*  479 */             qd.getQuerySpaces());
/*      */         }
/*      */         else
/*      */         {
/*  483 */           spec = new NativeSQLQuerySpecification(
/*  484 */             qd.getQueryString(), 
/*  485 */             qd.getQueryReturns(), 
/*  486 */             qd.getQuerySpaces());
/*      */         }
/*      */ 
/*  489 */         this.queryPlanCache.getNativeSQLQueryPlan(spec);
/*      */       }
/*      */       catch (QueryException e) {
/*  492 */         errors.put(queryName, e);
/*      */       }
/*      */       catch (MappingException e) {
/*  495 */         errors.put(queryName, e);
/*      */       }
/*      */     }
/*      */ 
/*  499 */     return errors;
/*      */   }
/*      */ 
/*      */   public StatelessSession openStatelessSession() {
/*  503 */     return new StatelessSessionImpl(null, this);
/*      */   }
/*      */ 
/*      */   public StatelessSession openStatelessSession(Connection connection) {
/*  507 */     return new StatelessSessionImpl(connection, this);
/*      */   }
/*      */ 
/*      */   private SessionImpl openSession(Connection connection, boolean autoClose, long timestamp, Interceptor sessionLocalInterceptor)
/*      */   {
/*  516 */     return new SessionImpl(
/*  517 */       connection, 
/*  518 */       this, 
/*  519 */       autoClose, 
/*  520 */       timestamp, 
/*  521 */       sessionLocalInterceptor == null ? this.interceptor : sessionLocalInterceptor, 
/*  522 */       this.settings.getDefaultEntityMode(), 
/*  523 */       this.settings.isFlushBeforeCompletionEnabled(), 
/*  524 */       this.settings.isAutoCloseSessionEnabled(), 
/*  525 */       this.settings.getConnectionReleaseMode());
/*      */   }
/*      */ 
/*      */   public Session openSession(Connection connection, Interceptor sessionLocalInterceptor)
/*      */   {
/*  530 */     return openSession(connection, false, -9223372036854775808L, sessionLocalInterceptor);
/*      */   }
/*      */ 
/*      */   public Session openSession(Interceptor sessionLocalInterceptor)
/*      */     throws HibernateException
/*      */   {
/*  539 */     long timestamp = this.settings.getRegionFactory().nextTimestamp();
/*  540 */     return openSession(null, true, timestamp, sessionLocalInterceptor);
/*      */   }
/*      */ 
/*      */   public Session openSession(Connection connection) {
/*  544 */     return openSession(connection, this.interceptor);
/*      */   }
/*      */ 
/*      */   public Session openSession() throws HibernateException {
/*  548 */     return openSession(this.interceptor);
/*      */   }
/*      */ 
/*      */   public Session openTemporarySession() throws HibernateException {
/*  552 */     return new SessionImpl(
/*  553 */       null, 
/*  554 */       this, 
/*  555 */       true, 
/*  556 */       this.settings.getRegionFactory().nextTimestamp(), 
/*  557 */       this.interceptor, 
/*  558 */       this.settings.getDefaultEntityMode(), 
/*  559 */       false, 
/*  560 */       false, 
/*  561 */       ConnectionReleaseMode.AFTER_STATEMENT);
/*      */   }
/*      */ 
/*      */   public Session openSession(Connection connection, boolean flushBeforeCompletionEnabled, boolean autoCloseSessionEnabled, ConnectionReleaseMode connectionReleaseMode)
/*      */     throws HibernateException
/*      */   {
/*  570 */     return new SessionImpl(
/*  571 */       connection, 
/*  572 */       this, 
/*  573 */       true, 
/*  574 */       this.settings.getRegionFactory().nextTimestamp(), 
/*  575 */       this.interceptor, 
/*  576 */       this.settings.getDefaultEntityMode(), 
/*  577 */       flushBeforeCompletionEnabled, 
/*  578 */       autoCloseSessionEnabled, 
/*  579 */       connectionReleaseMode);
/*      */   }
/*      */ 
/*      */   public Session getCurrentSession() throws HibernateException
/*      */   {
/*  584 */     if (this.currentSessionContext == null) {
/*  585 */       throw new HibernateException("No CurrentSessionContext configured!");
/*      */     }
/*  587 */     return this.currentSessionContext.currentSession();
/*      */   }
/*      */ 
/*      */   public EntityPersister getEntityPersister(String entityName) throws MappingException {
/*  591 */     EntityPersister result = (EntityPersister)this.entityPersisters.get(entityName);
/*  592 */     if (result == null) {
/*  593 */       throw new MappingException("Unknown entity: " + entityName);
/*      */     }
/*  595 */     return result;
/*      */   }
/*      */ 
/*      */   public CollectionPersister getCollectionPersister(String role) throws MappingException {
/*  599 */     CollectionPersister result = (CollectionPersister)this.collectionPersisters.get(role);
/*  600 */     if (result == null) {
/*  601 */       throw new MappingException("Unknown collection role: " + role);
/*      */     }
/*  603 */     return result;
/*      */   }
/*      */ 
/*      */   public Settings getSettings() {
/*  607 */     return this.settings;
/*      */   }
/*      */ 
/*      */   public Dialect getDialect() {
/*  611 */     return this.settings.getDialect();
/*      */   }
/*      */ 
/*      */   public Interceptor getInterceptor()
/*      */   {
/*  616 */     return this.interceptor;
/*      */   }
/*      */ 
/*      */   public TransactionFactory getTransactionFactory() {
/*  620 */     return this.settings.getTransactionFactory();
/*      */   }
/*      */ 
/*      */   public TransactionManager getTransactionManager() {
/*  624 */     return this.transactionManager;
/*      */   }
/*      */ 
/*      */   public SQLExceptionConverter getSQLExceptionConverter() {
/*  628 */     return this.settings.getSQLExceptionConverter();
/*      */   }
/*      */ 
/*      */   public Set getCollectionRolesByEntityParticipant(String entityName) {
/*  632 */     return (Set)this.collectionRolesByEntityParticipant.get(entityName);
/*      */   }
/*      */ 
/*      */   public Reference getReference() throws NamingException
/*      */   {
/*  637 */     log.debug("Returning a Reference to the SessionFactory");
/*  638 */     return new Reference(
/*  639 */       SessionFactoryImpl.class.getName(), 
/*  640 */       new StringRefAddr("uuid", this.uuid), 
/*  641 */       SessionFactoryObjectFactory.class.getName(), 
/*  642 */       null);
/*      */   }
/*      */ 
/*      */   private Object readResolve() throws ObjectStreamException
/*      */   {
/*  647 */     log.trace("Resolving serialized SessionFactory");
/*      */ 
/*  649 */     Object result = SessionFactoryObjectFactory.getInstance(this.uuid);
/*  650 */     if (result == null)
/*      */     {
/*  653 */       result = SessionFactoryObjectFactory.getNamedInstance(this.name);
/*  654 */       if (result == null) {
/*  655 */         throw new InvalidObjectException("Could not find a SessionFactory named: " + this.name);
/*      */       }
/*      */ 
/*  658 */       log.debug("resolved SessionFactory by name");
/*      */     }
/*      */     else
/*      */     {
/*  662 */       log.debug("resolved SessionFactory by uid");
/*      */     }
/*  664 */     return result;
/*      */   }
/*      */ 
/*      */   public NamedQueryDefinition getNamedQuery(String queryName) {
/*  668 */     return (NamedQueryDefinition)this.namedQueries.get(queryName);
/*      */   }
/*      */ 
/*      */   public NamedSQLQueryDefinition getNamedSQLQuery(String queryName) {
/*  672 */     return (NamedSQLQueryDefinition)this.namedSqlQueries.get(queryName);
/*      */   }
/*      */ 
/*      */   public ResultSetMappingDefinition getResultSetMapping(String resultSetName) {
/*  676 */     return (ResultSetMappingDefinition)this.sqlResultSetMappings.get(resultSetName);
/*      */   }
/*      */ 
/*      */   public Type getIdentifierType(String className) throws MappingException {
/*  680 */     return getEntityPersister(className).getIdentifierType();
/*      */   }
/*      */   public String getIdentifierPropertyName(String className) throws MappingException {
/*  683 */     return getEntityPersister(className).getIdentifierPropertyName();
/*      */   }
/*      */ 
/*      */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  687 */     log.trace("deserializing");
/*  688 */     in.defaultReadObject();
/*  689 */     log.debug("deserialized: " + this.uuid);
/*      */   }
/*      */ 
/*      */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  693 */     log.debug("serializing: " + this.uuid);
/*  694 */     out.defaultWriteObject();
/*  695 */     log.trace("serialized");
/*      */   }
/*      */ 
/*      */   public Type[] getReturnTypes(String queryString) throws HibernateException {
/*  699 */     return this.queryPlanCache.getHQLQueryPlan(queryString, false, CollectionHelper.EMPTY_MAP).getReturnMetadata().getReturnTypes();
/*      */   }
/*      */ 
/*      */   public String[] getReturnAliases(String queryString) throws HibernateException {
/*  703 */     return this.queryPlanCache.getHQLQueryPlan(queryString, false, CollectionHelper.EMPTY_MAP).getReturnMetadata().getReturnAliases();
/*      */   }
/*      */ 
/*      */   public ClassMetadata getClassMetadata(Class persistentClass) throws HibernateException {
/*  707 */     return getClassMetadata(persistentClass.getName());
/*      */   }
/*      */ 
/*      */   public CollectionMetadata getCollectionMetadata(String roleName) throws HibernateException {
/*  711 */     return (CollectionMetadata)this.collectionMetadata.get(roleName);
/*      */   }
/*      */ 
/*      */   public ClassMetadata getClassMetadata(String entityName) throws HibernateException {
/*  715 */     return (ClassMetadata)this.classMetadata.get(entityName);
/*      */   }
/*      */ 
/*      */   public String[] getImplementors(String className)
/*      */     throws MappingException
/*      */   {
//	/*      */       Class clazz;
	/*      */     Class clazz;
/*      */     try
/*      */     {
/*  727 */       clazz = ReflectHelper.classForName(className);
/*      */     }
/*      */     catch (ClassNotFoundException cnfe)
/*      */     {
/*  730 */       return new String[] { className };
/*      */     }
/*  733 */     ArrayList results = new ArrayList();
/*  734 */     Iterator iter = this.entityPersisters.values().iterator();
/*  735 */     while (iter.hasNext())
/*      */     {
/*  737 */       EntityPersister testPersister = (EntityPersister)iter.next();
/*  738 */       if ((testPersister instanceof Queryable)) {
/*  739 */         Queryable testQueryable = (Queryable)testPersister;
/*  740 */         String testClassName = testQueryable.getEntityName();
/*  741 */         boolean isMappedClass = className.equals(testClassName);
/*  742 */         if (testQueryable.isExplicitPolymorphism()) {
/*  743 */           if (isMappedClass) {
/*  744 */             return new String[] { className };
/*      */           }
/*      */ 
/*      */         }
/*  748 */         else if (isMappedClass) {
/*  749 */           results.add(testClassName);
/*      */         }
/*      */         else {
/*  752 */           Class mappedClass = testQueryable.getMappedClass(EntityMode.POJO);
/*  753 */           if ((mappedClass == null) || (!clazz.isAssignableFrom(mappedClass)))
/*      */             continue;
///*      */           boolean assignableSuperclass;
/*      */           boolean assignableSuperclass;
/*  755 */           if (testQueryable.isInherited()) {
/*  756 */             Class mappedSuperclass = getEntityPersister(testQueryable.getMappedSuperclass()).getMappedClass(EntityMode.POJO);
/*  757 */             assignableSuperclass = clazz.isAssignableFrom(mappedSuperclass);
/*      */           }
/*      */           else {
/*  760 */             assignableSuperclass = false;
/*      */           }
/*  762 */           if (!assignableSuperclass) {
/*  763 */             results.add(testClassName);
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  770 */     return (String[])results.toArray(new String[results.size()]);
/*      */   }
/*      */ 
/*      */   public String getImportedClassName(String className) {
/*  774 */     String result = (String)this.imports.get(className);
/*  775 */     if (result == null) {
/*      */       try {
/*  777 */         ReflectHelper.classForName(className);
/*  778 */         return className;
/*      */       }
/*      */       catch (ClassNotFoundException cnfe) {
/*  781 */         return null;
/*      */       }
/*      */     }
/*      */ 
/*  785 */     return result;
/*      */   }
/*      */ 
/*      */   public Map getAllClassMetadata() throws HibernateException
/*      */   {
/*  790 */     return this.classMetadata;
/*      */   }
/*      */ 
/*      */   public Map getAllCollectionMetadata() throws HibernateException {
/*  794 */     return this.collectionMetadata;
/*      */   }
/*      */ 
/*      */   public void close()
/*      */     throws HibernateException
/*      */   {
/*  813 */     if (this.isClosed) {
/*  814 */       log.trace("already closed");
/*  815 */       return;
/*      */     }
/*      */ 
/*  818 */     log.info("closing");
/*      */ 
/*  820 */     this.isClosed = true;
/*      */ 
/*  822 */     Iterator iter = this.entityPersisters.values().iterator();
/*  823 */     while (iter.hasNext()) {
/*  824 */       EntityPersister p = (EntityPersister)iter.next();
/*  825 */       if (p.hasCache()) {
/*  826 */         p.getCacheAccessStrategy().getRegion().destroy();
/*      */       }
/*      */     }
/*      */ 
/*  830 */     iter = this.collectionPersisters.values().iterator();
/*  831 */     while (iter.hasNext()) {
/*  832 */       CollectionPersister p = (CollectionPersister)iter.next();
/*  833 */       if (p.hasCache()) {
/*  834 */         p.getCacheAccessStrategy().getRegion().destroy();
/*      */       }
/*      */     }
/*      */ 
/*  838 */     if (this.settings.isQueryCacheEnabled()) {
/*  839 */       this.queryCache.destroy();
/*      */ 
/*  841 */       iter = this.queryCaches.values().iterator();
/*  842 */       while (iter.hasNext()) {
/*  843 */         QueryCache cache = (QueryCache)iter.next();
/*  844 */         cache.destroy();
/*      */       }
/*  846 */       this.updateTimestampsCache.destroy();
/*      */     }
/*      */ 
/*  849 */     this.settings.getRegionFactory().stop();
/*      */     try
/*      */     {
/*  852 */       this.settings.getConnectionProvider().close();
/*      */     }
/*      */     finally {
/*  855 */       SessionFactoryObjectFactory.removeInstance(this.uuid, this.name, this.properties);
/*      */     }
/*      */ 
/*  858 */     if (this.settings.isAutoDropSchema()) {
/*  859 */       this.schemaExport.drop(false, true);
/*      */     }
/*      */ 
/*  862 */     this.observer.sessionFactoryClosed(this);
/*  863 */     this.eventListeners.destroyListeners();
/*      */   }
/*      */ 
/*      */   public void evictEntity(String entityName, Serializable id) throws HibernateException {
/*  867 */     EntityPersister p = getEntityPersister(entityName);
/*  868 */     if (p.hasCache()) {
/*  869 */       if (log.isDebugEnabled()) {
/*  870 */         log.debug("evicting second-level cache: " + MessageHelper.infoString(p, id, this));
/*      */       }
/*  872 */       CacheKey cacheKey = new CacheKey(id, p.getIdentifierType(), p.getRootEntityName(), EntityMode.POJO, this);
/*  873 */       p.getCacheAccessStrategy().evict(cacheKey);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void evictEntity(String entityName) throws HibernateException {
/*  878 */     EntityPersister p = getEntityPersister(entityName);
/*  879 */     if (p.hasCache()) {
/*  880 */       if (log.isDebugEnabled()) {
/*  881 */         log.debug("evicting second-level cache: " + p.getEntityName());
/*      */       }
/*  883 */       p.getCacheAccessStrategy().evictAll();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void evict(Class persistentClass, Serializable id) throws HibernateException {
/*  888 */     EntityPersister p = getEntityPersister(persistentClass.getName());
/*  889 */     if (p.hasCache()) {
/*  890 */       if (log.isDebugEnabled()) {
/*  891 */         log.debug("evicting second-level cache: " + MessageHelper.infoString(p, id, this));
/*      */       }
/*  893 */       CacheKey cacheKey = new CacheKey(id, p.getIdentifierType(), p.getRootEntityName(), EntityMode.POJO, this);
/*  894 */       p.getCacheAccessStrategy().evict(cacheKey);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void evict(Class persistentClass) throws HibernateException {
/*  899 */     EntityPersister p = getEntityPersister(persistentClass.getName());
/*  900 */     if (p.hasCache()) {
/*  901 */       if (log.isDebugEnabled()) {
/*  902 */         log.debug("evicting second-level cache: " + p.getEntityName());
/*      */       }
/*  904 */       p.getCacheAccessStrategy().evictAll();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void evictCollection(String roleName, Serializable id) throws HibernateException {
/*  909 */     CollectionPersister p = getCollectionPersister(roleName);
/*  910 */     if (p.hasCache()) {
/*  911 */       if (log.isDebugEnabled()) {
/*  912 */         log.debug("evicting second-level cache: " + MessageHelper.collectionInfoString(p, id, this));
/*      */       }
/*  914 */       CacheKey cacheKey = new CacheKey(id, p.getKeyType(), p.getRole(), EntityMode.POJO, this);
/*  915 */       p.getCacheAccessStrategy().evict(cacheKey);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void evictCollection(String roleName) throws HibernateException {
/*  920 */     CollectionPersister p = getCollectionPersister(roleName);
/*  921 */     if (p.hasCache()) {
/*  922 */       if (log.isDebugEnabled()) {
/*  923 */         log.debug("evicting second-level cache: " + p.getRole());
/*      */       }
/*  925 */       p.getCacheAccessStrategy().evictAll();
/*      */     }
/*      */   }
/*      */ 
/*      */   public Type getReferencedPropertyType(String className, String propertyName) throws MappingException
/*      */   {
/*  931 */     return getEntityPersister(className).getPropertyType(propertyName);
/*      */   }
/*      */ 
/*      */   public ConnectionProvider getConnectionProvider() {
/*  935 */     return this.settings.getConnectionProvider();
/*      */   }
/*      */ 
/*      */   public UpdateTimestampsCache getUpdateTimestampsCache() {
/*  939 */     return this.updateTimestampsCache;
/*      */   }
/*      */ 
/*      */   public QueryCache getQueryCache() {
/*  943 */     return this.queryCache;
/*      */   }
/*      */ 
/*      */   public QueryCache getQueryCache(String regionName) throws HibernateException {
/*  947 */     if (regionName == null) {
/*  948 */       return getQueryCache();
/*      */     }
/*      */ 
/*  951 */     if (!this.settings.isQueryCacheEnabled()) {
/*  952 */       return null;
/*      */     }
/*      */ 
/*  955 */     synchronized (this.allCacheRegions) {
/*  956 */       QueryCache currentQueryCache = (QueryCache)this.queryCaches.get(regionName);
/*  957 */       if (currentQueryCache == null) {
/*  958 */         currentQueryCache = this.settings.getQueryCacheFactory().getQueryCache(regionName, this.updateTimestampsCache, this.settings, this.properties);
/*  959 */         this.queryCaches.put(regionName, currentQueryCache);
/*  960 */         this.allCacheRegions.put(currentQueryCache.getRegion().getName(), currentQueryCache.getRegion());
/*      */       }
/*  962 */       return currentQueryCache;
/*      */     }
/*      */   }
/*      */ 
/*      */   public Region getSecondLevelCacheRegion(String regionName) {
/*  967 */     synchronized (this.allCacheRegions) {
/*  968 */       return (Region)this.allCacheRegions.get(regionName);
/*      */     }
/*      */   }
/*      */ 
/*      */   public Map getAllSecondLevelCacheRegions() {
/*  973 */     synchronized (this.allCacheRegions) {
/*  974 */       return new HashMap(this.allCacheRegions);
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean isClosed() {
/*  979 */     return this.isClosed;
/*      */   }
/*      */ 
/*      */   public Statistics getStatistics() {
/*  983 */     return this.statistics;
/*      */   }
/*      */ 
/*      */   public StatisticsImplementor getStatisticsImplementor() {
/*  987 */     return this.statistics;
/*      */   }
/*      */ 
/*      */   public void evictQueries() throws HibernateException {
/*  991 */     if (this.settings.isQueryCacheEnabled())
/*  992 */       this.queryCache.clear();
/*      */   }
/*      */ 
/*      */   public void evictQueries(String cacheRegion) throws HibernateException
/*      */   {
/*  997 */     if (cacheRegion == null) {
/*  998 */       throw new NullPointerException("use the zero-argument form to evict the default query cache");
/*      */     }
/*      */ 
/* 1001 */     synchronized (this.allCacheRegions) {
/* 1002 */       if (this.settings.isQueryCacheEnabled()) {
/* 1003 */         QueryCache currentQueryCache = (QueryCache)this.queryCaches.get(cacheRegion);
/* 1004 */         if (currentQueryCache != null)
/* 1005 */           currentQueryCache.clear();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public FilterDefinition getFilterDefinition(String filterName)
/*      */     throws HibernateException
/*      */   {
/* 1013 */     FilterDefinition def = (FilterDefinition)this.filters.get(filterName);
/* 1014 */     if (def == null) {
/* 1015 */       throw new HibernateException("No such filter configured [" + filterName + "]");
/*      */     }
/* 1017 */     return def;
/*      */   }
/*      */ 
/*      */   public Set getDefinedFilterNames() {
/* 1021 */     return this.filters.keySet();
/*      */   }
/*      */ 
/*      */   public BatcherFactory getBatcherFactory() {
/* 1025 */     return this.settings.getBatcherFactory();
/*      */   }
/*      */ 
/*      */   public IdentifierGenerator getIdentifierGenerator(String rootEntityName) {
/* 1029 */     return (IdentifierGenerator)this.identifierGenerators.get(rootEntityName);
/*      */   }
/*      */ 
/*      */   private CurrentSessionContext buildCurrentSessionContext() {
/* 1033 */     String impl = this.properties.getProperty("hibernate.current_session_context_class");
/*      */ 
/* 1035 */     if ((impl == null) && (this.transactionManager != null)) {
/* 1036 */       impl = "jta";
/*      */     }
/*      */ 
/* 1039 */     if (impl == null) {
/* 1040 */       return null;
/*      */     }
/* 1042 */     if ("jta".equals(impl)) {
/* 1043 */       if (this.settings.getTransactionFactory().areCallbacksLocalToHibernateTransactions()) {
/* 1044 */         log.warn("JTASessionContext being used with JDBCTransactionFactory; auto-flush will not operate correctly with getCurrentSession()");
/*      */       }
/* 1046 */       return new JTASessionContext(this);
/*      */     }
/* 1048 */     if ("thread".equals(impl)) {
/* 1049 */       return new ThreadLocalSessionContext(this);
/*      */     }
/* 1051 */     if ("managed".equals(impl)) {
/* 1052 */       return new ManagedSessionContext(this);
/*      */     }
/*      */     try
/*      */     {
/* 1056 */       Class implClass = ReflectHelper.classForName(impl);
/* 1057 */       return 
/* 1059 */         (CurrentSessionContext)implClass
/* 1058 */         .getConstructor(new Class[] { SessionFactoryImplementor.class })
/* 1059 */         .newInstance(new Object[] { this });
/*      */     }
/*      */     catch (Throwable t) {
/* 1062 */       log.error("Unable to construct current session context [" + impl + "]", t);
/* 1063 */     }return null;
/*      */   }
/*      */ 
/*      */   public EventListeners getEventListeners()
/*      */   {
/* 1070 */     return this.eventListeners;
/*      */   }
/*      */ 
/*      */   public EntityNotFoundDelegate getEntityNotFoundDelegate() {
/* 1074 */     return this.entityNotFoundDelegate;
/*      */   }
/*      */ 
/*      */   void serialize(ObjectOutputStream oos)
/*      */     throws IOException
/*      */   {
/* 1084 */     oos.writeUTF(this.uuid);
/* 1085 */     oos.writeBoolean(this.name != null);
/* 1086 */     if (this.name != null)
/* 1087 */       oos.writeUTF(this.name);
/*      */   }
/*      */ 
/*      */   static SessionFactoryImpl deserialize(ObjectInputStream ois)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1100 */     String uuid = ois.readUTF();
/* 1101 */     boolean isNamed = ois.readBoolean();
/* 1102 */     String name = null;
/* 1103 */     if (isNamed) {
/* 1104 */       name = ois.readUTF();
/*      */     }
/* 1106 */     Object result = SessionFactoryObjectFactory.getInstance(uuid);
/* 1107 */     if (result == null) {
/* 1108 */       log.trace("could not locate session factory by uuid [" + uuid + "] during session deserialization; trying name");
/* 1109 */       if (isNamed) {
/* 1110 */         result = SessionFactoryObjectFactory.getNamedInstance(name);
/*      */       }
/* 1112 */       if (result == null) {
/* 1113 */         throw new InvalidObjectException("could not resolve session factory during session deserialization [uuid=" + uuid + ", name=" + name + "]");
/*      */       }
/*      */     }
/* 1116 */     return (SessionFactoryImpl)result;
/*      */   }
/*      */ 
/*      */   public SQLFunctionRegistry getSqlFunctionRegistry() {
/* 1120 */     return this.sqlFunctionRegistry;
/*      */   }
/*      */ 
/*      */   public void addNewConfig(Configuration cfg)
/*      */   {
/* 1125 */     log.info("add NewConfig.....");
/*      */ 
/* 1127 */     Mapping mapping = this.configuration.getMapping();
/* 1128 */     this.filters.putAll(cfg.getFilterDefinitions());
/*      */ 
/* 1130 */     Iterator classes = cfg.getClassMappings();
/* 1131 */     while (classes.hasNext()) {
/* 1132 */       PersistentClass model = (PersistentClass)classes.next();
/*      */ 
/* 1134 */       if (!model.isInherited()) {
/* 1135 */         IdentifierGenerator generator = model.getIdentifier().createIdentifierGenerator(
/* 1136 */           this.settings.getDialect(), 
/* 1137 */           this.settings.getDefaultCatalogName(), 
/* 1138 */           this.settings.getDefaultSchemaName(), 
/* 1139 */           (RootClass)model);
/*      */ 
/* 1141 */         this.identifierGenerators.put(model.getEntityName(), generator);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1149 */     String cacheRegionPrefix = this.settings.getCacheRegionPrefix() + ".";
/*      */ 
/* 1151 */     Map entityAccessStrategies = new HashMap();
/* 1152 */     Map tmpEntityPersisters = new HashMap();
/* 1153 */     Map tmpClassMetadata = new HashMap();
/*      */ 
/* 1156 */     this.configuration.getClassMap().putAll(cfg.getClassMap());
/* 1157 */     classes = cfg.getClassMappings();
/*      */ 
/* 1159 */     while (classes.hasNext())
/*      */     {
/* 1161 */       PersistentClass model = (PersistentClass)classes.next();
/*      */ 
/* 1163 */       model.prepareTemporaryTables(mapping, this.settings.getDialect());
/* 1164 */       String cacheRegionName = cacheRegionPrefix + model.getRootClass().getCacheRegionName();
/*      */ 
/* 1166 */       System.out.println("cacheRegionName:" + cacheRegionName);
/*      */ 
/* 1169 */       EntityRegionAccessStrategy accessStrategy = (EntityRegionAccessStrategy)entityAccessStrategies.get(cacheRegionName);
/* 1170 */       if ((accessStrategy == null) && (this.settings.isSecondLevelCacheEnabled())) {
/* 1171 */         AccessType accessType = AccessType.parse(model.getCacheConcurrencyStrategy());
/* 1172 */         if (accessType != null)
/*      */         {
/* 1174 */           log.trace("Building cache for entity data [" + model.getEntityName() + "]");
/* 1175 */           EntityRegion entityRegion = this.settings.getRegionFactory().buildEntityRegion(cacheRegionName, this.properties, CacheDataDescriptionImpl.decode(model));
/* 1176 */           accessStrategy = entityRegion.buildAccessStrategy(accessType);
/* 1177 */           entityAccessStrategies.put(cacheRegionName, accessStrategy);
/* 1178 */           this.allCacheRegions.put(cacheRegionName, entityRegion);
/*      */         }
/*      */       }
/* 1181 */       EntityPersister cp = PersisterFactory.createClassPersister(model, accessStrategy, this, cfg.getMapping());
/* 1182 */       tmpEntityPersisters.put(model.getEntityName(), cp);
/* 1183 */       tmpClassMetadata.put(model.getEntityName(), cp.getClassMetadata());
/*      */     }
/*      */ 
/* 1188 */     this.namedQueries.putAll(cfg.getNamedQueries());
/* 1189 */     this.namedSqlQueries.putAll(cfg.getNamedSQLQueries());
/* 1190 */     this.sqlResultSetMappings.putAll(cfg.getSqlResultSetMappings());
/* 1191 */     this.imports.putAll(cfg.getImports());
/*      */ 
/* 1193 */     this.entityPersisters.putAll(tmpEntityPersisters);
/*      */ 
/* 1195 */     this.classMetadata.putAll(tmpClassMetadata);
/*      */ 
/* 1197 */     Map tmpEntityToCollectionRoleMap = new HashMap();
/* 1198 */     Map tempCollectionPersisters = new HashMap();
/*      */ 
/* 1200 */     this.configuration.getCollectionMap().putAll(cfg.getCollectionMap());
/* 1201 */     Iterator collections = cfg.getCollectionMappings();
/*      */ 
/* 1203 */     while (collections.hasNext()) {
/* 1204 */       org.hibernate.mapping.Collection model = (org.hibernate.mapping.Collection)collections.next();
/* 1205 */       String cacheRegionName = cacheRegionPrefix + model.getCacheRegionName();
/* 1206 */       AccessType accessType = AccessType.parse(model.getCacheConcurrencyStrategy());
/* 1207 */       CollectionRegionAccessStrategy accessStrategy = null;
/* 1208 */       if ((accessType != null) && (this.settings.isSecondLevelCacheEnabled())) {
/* 1209 */         log.trace("Building cache for collection data [" + model.getRole() + "]");
/* 1210 */         CollectionRegion collectionRegion = this.settings.getRegionFactory().buildCollectionRegion(cacheRegionName, this.properties, CacheDataDescriptionImpl.decode(model));
/* 1211 */         accessStrategy = collectionRegion.buildAccessStrategy(accessType);
/* 1212 */         entityAccessStrategies.put(cacheRegionName, accessStrategy);
/* 1213 */         this.allCacheRegions.put(cacheRegionName, collectionRegion);
/*      */       }
/* 1215 */       CollectionPersister persister = PersisterFactory.createCollectionPersister(getConfiguration(), model, accessStrategy, this);
/* 1216 */       tempCollectionPersisters.put(model.getRole(), persister.getCollectionMetadata());
/* 1217 */       Type indexType = persister.getIndexType();
/* 1218 */       if ((indexType != null) && (indexType.isAssociationType()) && (!indexType.isAnyType())) {
/* 1219 */         String entityName = ((AssociationType)indexType).getAssociatedEntityName(this);
/* 1220 */         Set roles = (Set)tmpEntityToCollectionRoleMap.get(entityName);
/* 1221 */         if (roles == null) {
/* 1222 */           roles = new HashSet();
/* 1223 */           tmpEntityToCollectionRoleMap.put(entityName, roles);
/*      */         }
/* 1225 */         roles.add(persister.getRole());
/*      */       }
/* 1227 */       Type elementType = persister.getElementType();
/* 1228 */       if ((elementType.isAssociationType()) && (!elementType.isAnyType())) {
/* 1229 */         String entityName = ((AssociationType)elementType).getAssociatedEntityName(this);
/* 1230 */         Set roles = (Set)tmpEntityToCollectionRoleMap.get(entityName);
/* 1231 */         if (roles == null) {
/* 1232 */           roles = new HashSet();
/* 1233 */           tmpEntityToCollectionRoleMap.put(entityName, roles);
/*      */         }
/* 1235 */         roles.add(persister.getRole());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1240 */     this.collectionPersisters.putAll(tempCollectionPersisters);
/*      */ 
/* 1242 */     Iterator itr = tmpEntityToCollectionRoleMap.entrySet().iterator();
/* 1243 */     while (itr.hasNext()) {
/* 1244 */       Map.Entry entry = (Map.Entry)itr.next();
/* 1245 */       entry.setValue(Collections.unmodifiableSet((Set)entry.getValue()));
/*      */     }
/*      */ 
/* 1248 */     this.collectionRolesByEntityParticipant.putAll(tmpEntityToCollectionRoleMap);
/*      */ 
/* 1251 */     Iterator iter = tmpEntityPersisters.values().iterator();
/* 1252 */     while (iter.hasNext()) {
/* 1253 */       ((EntityPersister)iter.next()).postInstantiate();
/*      */     }
/* 1255 */     iter = tempCollectionPersisters.values().iterator();
/* 1256 */     while (iter.hasNext()) {
/* 1257 */       ((CollectionPersister)iter.next()).postInstantiate();
/*      */     }
/*      */ 
/* 1260 */     this.queryPlanCache = new QueryPlanCache(this);
/*      */   }
/*      */ 
/*      */   public Configuration getConfiguration()
/*      */   {
/* 1265 */     return this.configuration;
/*      */   }
/*      */ }

/* Location:           E:\Workspace\Template Projects\estbpm-orcl\estbpm\WEB-INF\classes\
 * Qualified Name:     org.hibernate.impl.SessionFactoryImpl
 * JD-Core Version:    0.6.0
 */
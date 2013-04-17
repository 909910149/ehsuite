/*      */ package org.hibernate.cfg;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.Serializable;
/*      */ import java.io.StringReader;
/*      */ import java.lang.reflect.Array;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Enumeration;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Properties;
/*      */ import java.util.Set;
/*      */ import java.util.TreeMap;
/*      */ import java.util.jar.JarFile;
/*      */ import java.util.zip.ZipEntry;
/*      */ import org.dom4j.Attribute;
/*      */ import org.dom4j.DocumentException;
/*      */ import org.dom4j.Element;
/*      */ import org.dom4j.io.DOMReader;
/*      */ import org.dom4j.io.SAXReader;
/*      */ import org.hibernate.EmptyInterceptor;
/*      */ import org.hibernate.HibernateException;
/*      */ import org.hibernate.Interceptor;
/*      */ import org.hibernate.InvalidMappingException;
/*      */ import org.hibernate.MappingException;
/*      */ import org.hibernate.MappingNotFoundException;
/*      */ import org.hibernate.SessionFactory;
/*      */ import org.hibernate.SessionFactoryObserver;
/*      */ import org.hibernate.dialect.Dialect;
/*      */ import org.hibernate.dialect.MySQLDialect;
/*      */ import org.hibernate.dialect.function.SQLFunction;
/*      */ import org.hibernate.engine.FilterDefinition;
/*      */ import org.hibernate.engine.Mapping;
/*      */ import org.hibernate.event.AutoFlushEventListener;
/*      */ import org.hibernate.event.DeleteEventListener;
/*      */ import org.hibernate.event.DirtyCheckEventListener;
/*      */ import org.hibernate.event.EventListeners;
/*      */ import org.hibernate.event.EvictEventListener;
/*      */ import org.hibernate.event.FlushEntityEventListener;
/*      */ import org.hibernate.event.FlushEventListener;
/*      */ import org.hibernate.event.InitializeCollectionEventListener;
/*      */ import org.hibernate.event.LoadEventListener;
/*      */ import org.hibernate.event.LockEventListener;
/*      */ import org.hibernate.event.MergeEventListener;
/*      */ import org.hibernate.event.PersistEventListener;
/*      */ import org.hibernate.event.PostCollectionRecreateEventListener;
/*      */ import org.hibernate.event.PostCollectionRemoveEventListener;
/*      */ import org.hibernate.event.PostCollectionUpdateEventListener;
/*      */ import org.hibernate.event.PostDeleteEventListener;
/*      */ import org.hibernate.event.PostInsertEventListener;
/*      */ import org.hibernate.event.PostLoadEventListener;
/*      */ import org.hibernate.event.PostUpdateEventListener;
/*      */ import org.hibernate.event.PreCollectionRecreateEventListener;
/*      */ import org.hibernate.event.PreCollectionRemoveEventListener;
/*      */ import org.hibernate.event.PreCollectionUpdateEventListener;
/*      */ import org.hibernate.event.PreDeleteEventListener;
/*      */ import org.hibernate.event.PreInsertEventListener;
/*      */ import org.hibernate.event.PreLoadEventListener;
/*      */ import org.hibernate.event.PreUpdateEventListener;
/*      */ import org.hibernate.event.RefreshEventListener;
/*      */ import org.hibernate.event.ReplicateEventListener;
/*      */ import org.hibernate.event.SaveOrUpdateEventListener;
/*      */ import org.hibernate.id.IdentifierGenerator;
/*      */ import org.hibernate.id.PersistentIdentifierGenerator;
/*      */ import org.hibernate.impl.SessionFactoryImpl;
/*      */ import org.hibernate.mapping.AuxiliaryDatabaseObject;
/*      */ import org.hibernate.mapping.ForeignKey;
/*      */ import org.hibernate.mapping.IdentifierCollection;
/*      */ import org.hibernate.mapping.Index;
/*      */ import org.hibernate.mapping.KeyValue;
/*      */ import org.hibernate.mapping.PersistentClass;
/*      */ import org.hibernate.mapping.Property;
/*      */ import org.hibernate.mapping.RootClass;
/*      */ import org.hibernate.mapping.SimpleValue;
/*      */ import org.hibernate.mapping.Table;
/*      */ import org.hibernate.mapping.UniqueKey;
/*      */ import org.hibernate.proxy.EntityNotFoundDelegate;
/*      */ import org.hibernate.secure.JACCConfiguration;
/*      */ import org.hibernate.tool.hbm2ddl.DatabaseMetadata;
/*      */ import org.hibernate.tool.hbm2ddl.TableMetadata;
/*      */ import org.hibernate.type.SerializationException;
/*      */ import org.hibernate.type.Type;
/*      */ import org.hibernate.util.ArrayHelper;
/*      */ import org.hibernate.util.CollectionHelper;
/*      */ import org.hibernate.util.ConfigHelper;
/*      */ import org.hibernate.util.PropertiesHelper;
/*      */ import org.hibernate.util.ReflectHelper;
/*      */ import org.hibernate.util.SerializationHelper;
/*      */ import org.hibernate.util.StringHelper;
/*      */ import org.hibernate.util.XMLHelper;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ import org.xml.sax.EntityResolver;
/*      */ import org.xml.sax.InputSource;
/*      */ 
/*      */ public class Configuration
/*      */   implements Serializable
/*      */ {
/*  151 */   private static Logger log = LoggerFactory.getLogger(Configuration.class);
/*      */   protected Map classes;
/*      */   protected Map imports;
/*      */   protected Map collections;
/*      */   protected Map tables;
/*      */   protected List auxiliaryDatabaseObjects;
/*      */   protected Map sqlFunctions;
/*      */   protected Map namedQueries;
/*      */   protected Map namedSqlQueries;
/*      */   protected Map sqlResultSetMappings;
/*      */   protected Map filterDefinitions;
/*      */   protected List secondPasses;
/*      */   protected List propertyReferences;
/*      */   protected Map extendsQueue;
/*      */   protected Map tableNameBinding;
/*      */   protected Map columnNameBindingPerTable;
/*      */   private Interceptor interceptor;
/*      */   private Properties properties;
/*      */   private EntityResolver entityResolver;
/*      */   private EntityNotFoundDelegate entityNotFoundDelegate;
/*      */   protected transient XMLHelper xmlHelper;
/*      */   protected transient Map typeDefs;
/*      */   protected NamingStrategy namingStrategy;
/*      */   private EventListeners eventListeners;
/*      */   protected final SettingsFactory settingsFactory;
/*      */   private SessionFactoryObserver sessionFactoryObserver;
/*  218 */   private transient Mapping mapping = buildMapping();
/*      */ 
/*      */   public void clear()
/*      */   {
/*  189 */     reset();
/*      */   }
/*      */ 
/*      */   protected void reset() {
/*  193 */     this.classes = new HashMap();
/*  194 */     this.imports = new HashMap();
/*  195 */     this.collections = new HashMap();
/*  196 */     this.tables = new TreeMap();
/*  197 */     this.namedQueries = new HashMap();
/*  198 */     this.namedSqlQueries = new HashMap();
/*  199 */     this.sqlResultSetMappings = new HashMap();
/*  200 */     this.xmlHelper = new XMLHelper();
/*  201 */     this.typeDefs = new HashMap();
/*  202 */     this.propertyReferences = new ArrayList();
/*  203 */     this.secondPasses = new ArrayList();
/*  204 */     this.interceptor = EmptyInterceptor.INSTANCE;
/*  205 */     this.properties = Environment.getProperties();
/*  206 */     this.entityResolver = XMLHelper.DEFAULT_DTD_RESOLVER;
/*  207 */     this.eventListeners = new EventListeners();
/*  208 */     this.filterDefinitions = new HashMap();
/*      */ 
/*  210 */     this.extendsQueue = new HashMap();
/*  211 */     this.auxiliaryDatabaseObjects = new ArrayList();
/*  212 */     this.tableNameBinding = new HashMap();
/*  213 */     this.columnNameBindingPerTable = new HashMap();
/*  214 */     this.namingStrategy = DefaultNamingStrategy.INSTANCE;
/*  215 */     this.sqlFunctions = new HashMap();
/*      */   }
/*      */ 
/*      */   protected Configuration(SettingsFactory settingsFactory)
/*      */   {
/*  223 */     this.settingsFactory = settingsFactory;
/*  224 */     reset();
/*      */   }
/*      */ 
/*      */   public Configuration() {
/*  228 */     this(new SettingsFactory());
/*      */   }
/*      */ 
/*      */   public Iterator getClassMappings()
/*      */   {
/*  237 */     return this.classes.values().iterator();
/*      */   }
/*      */ 
/*      */   public Iterator getCollectionMappings()
/*      */   {
/*  246 */     return this.collections.values().iterator();
/*      */   }
/*      */ 
/*      */   public Iterator getTableMappings()
/*      */   {
/*  255 */     return this.tables.values().iterator();
/*      */   }
/*      */ 
/*      */   public PersistentClass getClassMapping(String entityName)
/*      */   {
/*  265 */     return (PersistentClass)this.classes.get(entityName);
/*      */   }
/*      */ 
/*      */   public org.hibernate.mapping.Collection getCollectionMapping(String role)
/*      */   {
/*  275 */     return (org.hibernate.mapping.Collection)this.collections.get(role);
/*      */   }
/*      */ 
/*      */   public void setEntityResolver(EntityResolver entityResolver)
/*      */   {
/*  286 */     this.entityResolver = entityResolver;
/*      */   }
/*      */ 
/*      */   public EntityResolver getEntityResolver() {
/*  290 */     return this.entityResolver;
/*      */   }
/*      */ 
/*      */   public EntityNotFoundDelegate getEntityNotFoundDelegate()
/*      */   {
/*  300 */     return this.entityNotFoundDelegate;
/*      */   }
/*      */ 
/*      */   public void setEntityNotFoundDelegate(EntityNotFoundDelegate entityNotFoundDelegate)
/*      */   {
/*  311 */     this.entityNotFoundDelegate = entityNotFoundDelegate;
/*      */   }
/*      */ 
/*      */   public Configuration addFile(String xmlFile)
/*      */     throws MappingException
/*      */   {
/*  324 */     return addFile(new File(xmlFile));
/*      */   }
/*      */ 
/*      */   public Configuration addFile(File xmlFile)
/*      */     throws MappingException
/*      */   {
/*  336 */     log.info("Reading mappings from file: " + xmlFile.getPath());
/*  337 */     if (!xmlFile.exists())
/*  338 */       throw new MappingNotFoundException("file", xmlFile.toString());
/*      */     try
/*      */     {
/*  341 */       List errors = new ArrayList();
/*  342 */       org.dom4j.Document doc = this.xmlHelper.createSAXReader(xmlFile.toString(), errors, this.entityResolver).read(xmlFile);
/*  343 */       if (errors.size() != 0) {
/*  344 */         throw new InvalidMappingException("file", xmlFile.toString(), (Throwable)errors.get(0));
/*      */       }
/*  346 */       add(doc);
/*  347 */       return this;
/*      */     }
/*      */     catch (InvalidMappingException e) {
/*  350 */       throw e;
/*      */     }
/*      */     catch (MappingNotFoundException e) {
/*  353 */       throw e;
/*      */     } catch (Exception e) {
	/*  356 */     throw new InvalidMappingException("file", xmlFile.toString(), e);
/*      */     }
/*      */   }
/*      */ 
/*      */   public Configuration addCacheableFile(File xmlFile)
/*      */     throws MappingException
/*      */   {
/*      */     try
/*      */     {
/*  378 */       File cachedFile = new File(xmlFile.getAbsolutePath() + ".bin");
/*  379 */       org.dom4j.Document doc = null;
/*      */ 
/*  381 */       boolean useCachedFile = (xmlFile.exists()) && 
/*  382 */         (cachedFile.exists()) && 
/*  383 */         (xmlFile.lastModified() < cachedFile.lastModified());
/*      */ 
/*  385 */       if (useCachedFile) {
/*      */         try {
/*  387 */           log.info("Reading mappings from cache file: " + cachedFile);
/*  388 */           doc = (org.dom4j.Document)SerializationHelper.deserialize(new FileInputStream(cachedFile));
/*      */         }
/*      */         catch (SerializationException e) {
/*  391 */           log.warn("Could not deserialize cache file: " + cachedFile.getPath(), e);
/*      */         }
/*      */         catch (FileNotFoundException e) {
/*  394 */           log.warn("I/O reported cached file could not be found : " + cachedFile.getPath(), e);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  399 */       if (doc == null) {
/*  400 */         if (!xmlFile.exists()) {
/*  401 */           throw new MappingNotFoundException("file", xmlFile.toString());
/*      */         }
/*      */ 
/*  404 */         log.info("Reading mappings from file: " + xmlFile);
/*  405 */         List errors = new ArrayList();
/*      */         try {
/*  407 */           doc = this.xmlHelper.createSAXReader(xmlFile.getAbsolutePath(), errors, this.entityResolver).read(xmlFile);
/*  408 */           if (errors.size() != 0)
/*  409 */             throw new MappingException("invalid mapping", (Throwable)errors.get(0));
/*      */         }
/*      */         catch (DocumentException e)
/*      */         {
/*  413 */           throw new MappingException("invalid mapping", e);
/*      */         }
/*      */         try
/*      */         {
/*  417 */           log.debug("Writing cache file for: " + xmlFile + " to: " + cachedFile);
/*  418 */           SerializationHelper.serialize((Serializable)doc, new FileOutputStream(cachedFile));
/*      */         }
/*      */         catch (SerializationException e) {
/*  421 */           log.warn("Could not write cached file: " + cachedFile, e);
/*      */         }
/*      */         catch (FileNotFoundException e) {
/*  424 */           log.warn("I/O reported error writing cached file : " + cachedFile.getPath(), e);
/*      */         }
/*      */       }
/*      */ 
/*  428 */       add(doc);
/*  429 */       return this;
/*      */     }
/*      */     catch (InvalidMappingException e)
/*      */     {
/*  433 */       throw e;
/*      */     }
/*      */     catch (MappingNotFoundException e) {
/*  436 */       throw e;
/*      */     } catch (Exception e) {
	/*  439 */     throw new InvalidMappingException("file", xmlFile.toString(), e);
/*      */     }
/*      */   }
/*      */ 
/*      */   public Configuration addCacheableFile(String xmlFile)
/*      */     throws MappingException
/*      */   {
/*  454 */     return addCacheableFile(new File(xmlFile));
/*      */   }
/*      */ 
/*      */   public Configuration addXML(String xml)
/*      */     throws MappingException
/*      */   {
/*  467 */     if (log.isDebugEnabled())
/*  468 */       log.debug("Mapping XML:\n" + xml);
/*      */     try
/*      */     {
/*  471 */       List errors = new ArrayList();
/*  472 */       org.dom4j.Document doc = this.xmlHelper.createSAXReader("XML String", errors, this.entityResolver)
/*  473 */         .read(new StringReader(xml));
/*  474 */       if (errors.size() != 0) {
/*  475 */         throw new MappingException("invalid mapping", (Throwable)errors.get(0));
/*      */       }
/*  477 */       add(doc);
/*      */     }
/*      */     catch (DocumentException e) {
/*  480 */       throw new MappingException("Could not parse mapping document in XML string", e);
/*      */     }
/*  482 */     return this;
/*      */   }
/*      */ 
/*      */   public Configuration addURL(URL url)
/*      */     throws MappingException
/*      */   {
/*  494 */     if (log.isDebugEnabled())
/*  495 */       log.debug("Reading mapping document from URL:" + url.toExternalForm());
/*      */     try
/*      */     {
/*  498 */       addInputStream(url.openStream());
/*      */     }
/*      */     catch (InvalidMappingException e) {
/*  501 */       throw new InvalidMappingException("URL", url.toExternalForm(), e.getCause());
/*      */     }
/*      */     catch (Exception e) {
/*  504 */       throw new InvalidMappingException("URL", url.toExternalForm(), e);
/*      */     }
/*  506 */     return this;
/*      */   }
/*      */ 
/*      */   public Configuration addDocument(org.w3c.dom.Document doc)
/*      */     throws MappingException
/*      */   {
/*  518 */     if (log.isDebugEnabled()) {
/*  519 */       log.debug("Mapping document:\n" + doc);
/*      */     }
/*  521 */     add(this.xmlHelper.createDOMReader().read(doc));
/*  522 */     return this;
/*      */   }
/*      */ 
/*      */   public Configuration addInputStream(InputStream xmlInputStream)
/*      */     throws MappingException
/*      */   {
/*      */     try
/*      */     {
/*  535 */       List errors = new ArrayList();
/*  536 */       org.dom4j.Document doc = this.xmlHelper.createSAXReader("XML InputStream", errors, this.entityResolver)
/*  537 */         .read(new InputSource(xmlInputStream));
/*  538 */       if (errors.size() != 0) {
/*  539 */         throw new InvalidMappingException("invalid mapping", null, (Throwable)errors.get(0));
/*      */       }
/*  541 */       add(doc);
/*  542 */       Configuration localConfiguration = this;
/*      */       return localConfiguration;
/*      */     }
/*      */     catch (DocumentException e) {
/*  545 */       throw new InvalidMappingException("input stream", null, e);
/*      */     }
/*      */     finally {
/*      */       try {
/*  549 */         xmlInputStream.close();
/*      */       }
/*      */       catch (IOException ioe) {
/*  552 */         log.warn("Could not close input stream", ioe);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public Configuration addResource(String resourceName, ClassLoader classLoader)
/*      */     throws MappingException
/*      */   {
/*  567 */     log.info("Reading mappings from resource: " + resourceName);
/*  568 */     InputStream rsrc = classLoader.getResourceAsStream(resourceName);
/*  569 */     if (rsrc == null)
/*  570 */       throw new MappingNotFoundException("resource", resourceName);
/*      */     try
/*      */     {
/*  573 */       return addInputStream(rsrc);
/*      */     } catch (MappingException me) {
	/*  576 */     throw new InvalidMappingException("resource", resourceName, me);
/*      */     }
/*      */   }
/*      */ 
/*      */   public Configuration addResource(String resourceName)
/*      */     throws MappingException
/*      */   {
/*  590 */     log.info("Reading mappings from resource : " + resourceName);
/*  591 */     ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
/*  592 */     InputStream rsrc = null;
/*  593 */     if (contextClassLoader != null) {
/*  594 */       rsrc = contextClassLoader.getResourceAsStream(resourceName);
/*      */     }
/*  596 */     if (rsrc == null) {
/*  597 */       rsrc = Environment.class.getClassLoader().getResourceAsStream(resourceName);
/*      */     }
/*  599 */     if (rsrc == null)
/*  600 */       throw new MappingNotFoundException("resource", resourceName);
/*      */     try
/*      */     {
/*  603 */       return addInputStream(rsrc);
/*      */     } catch (MappingException me) {
	/*  606 */     throw new InvalidMappingException("resource", resourceName, me);
/*      */     }
/*      */   }
/*      */ 
/*      */   public Configuration addClass(Class persistentClass)
/*      */     throws MappingException
/*      */   {
/*  621 */     String mappingResourceName = persistentClass.getName().replace('.', '/') + ".hbm.xml";
/*  622 */     log.info("Reading mappings from resource: " + mappingResourceName);
/*  623 */     return addResource(mappingResourceName, persistentClass.getClassLoader());
/*      */   }
/*      */ 
/*      */   public Configuration addJar(File jar)
/*      */     throws MappingException
/*      */   {
/*  637 */     log.info("Searching for mapping documents in jar: " + jar.getName());
/*  638 */     JarFile jarFile = null;
/*      */     try {
/*      */       try {
/*  641 */         jarFile = new JarFile(jar);
/*      */       }
/*      */       catch (IOException ioe) {
/*  644 */         throw new InvalidMappingException(
/*  645 */           "Could not read mapping documents from jar: " + jar.getName(), "jar", jar.getName(), 
/*  646 */           ioe);
/*      */       }
/*      */ 
/*  649 */       Enumeration jarEntries = jarFile.entries();
/*  650 */       while (jarEntries.hasMoreElements()) {
/*  651 */         ZipEntry ze = (ZipEntry)jarEntries.nextElement();
/*  652 */         if (ze.getName().endsWith(".hbm.xml")) {
/*  653 */           log.info("Found mapping document in jar: " + ze.getName());
/*      */           try {
/*  655 */             addInputStream(jarFile.getInputStream(ze));
/*      */           }
/*      */           catch (Exception e) {
/*  658 */             throw new InvalidMappingException(
/*  659 */               "Could not read mapping documents from jar: " + jar.getName(), 
/*  660 */               "jar", 
/*  661 */               jar.getName(), 
/*  662 */               e);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*      */       try {
/*  670 */         if (jarFile != null)
/*  671 */           jarFile.close();
/*      */       }
/*      */       catch (IOException ioe)
/*      */       {
/*  675 */         log.error("could not close jar", ioe);
/*      */       }
/*      */     }
/*      */ 
/*  679 */     return this;
/*      */   }
/*      */ 
/*      */   public Configuration addDirectory(File dir)
/*      */     throws MappingException
/*      */   {
/*  693 */     File[] files = dir.listFiles();
/*  694 */     for (int i = 0; i < files.length; i++) {
/*  695 */       if (files[i].isDirectory()) {
/*  696 */         addDirectory(files[i]);
/*      */       }
/*  698 */       else if (files[i].getName().endsWith(".hbm.xml")) {
/*  699 */         addFile(files[i]);
/*      */       }
/*      */     }
/*  702 */     return this;
/*      */   }
/*      */ 
/*      */   protected void add(org.dom4j.Document doc) throws MappingException {
/*  706 */     HbmBinder.bindRoot(doc, createMappings(), CollectionHelper.EMPTY_MAP);
/*      */   }
/*      */ 
/*      */   public Mappings createMappings()
/*      */   {
/*  714 */     return new Mappings(
/*  715 */       this.classes, 
/*  716 */       this.collections, 
/*  717 */       this.tables, 
/*  718 */       this.namedQueries, 
/*  719 */       this.namedSqlQueries, 
/*  720 */       this.sqlResultSetMappings, 
/*  721 */       this.imports, 
/*  722 */       this.secondPasses, 
/*  723 */       this.propertyReferences, 
/*  724 */       this.namingStrategy, 
/*  725 */       this.typeDefs, 
/*  726 */       this.filterDefinitions, 
/*  727 */       this.extendsQueue, 
/*  728 */       this.auxiliaryDatabaseObjects, 
/*  729 */       this.tableNameBinding, 
/*  730 */       this.columnNameBindingPerTable);
/*      */   }
/*      */ 
/*      */   private Iterator iterateGenerators(Dialect dialect)
/*      */     throws MappingException
/*      */   {
/*  737 */     TreeMap generators = new TreeMap();
/*  738 */     String defaultCatalog = this.properties.getProperty("hibernate.default_catalog");
/*  739 */     String defaultSchema = this.properties.getProperty("hibernate.default_schema");
/*      */ 
/*  741 */     Iterator iter = this.classes.values().iterator();
/*  742 */     while (iter.hasNext()) {
/*  743 */       PersistentClass pc = (PersistentClass)iter.next();
/*      */ 
/*  745 */       if (pc.isInherited())
/*      */         continue;
/*  747 */       IdentifierGenerator ig = pc.getIdentifier()
/*  748 */         .createIdentifierGenerator(
/*  749 */         dialect, 
/*  750 */         defaultCatalog, 
/*  751 */         defaultSchema, 
/*  752 */         (RootClass)pc);
/*      */ 
/*  755 */       if ((ig instanceof PersistentIdentifierGenerator)) {
/*  756 */         generators.put(((PersistentIdentifierGenerator)ig).generatorKey(), ig);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  762 */     iter = this.collections.values().iterator();
/*  763 */     while (iter.hasNext()) {
/*  764 */       org.hibernate.mapping.Collection collection = (org.hibernate.mapping.Collection)iter.next();
/*      */ 
/*  766 */       if (!collection.isIdentified())
/*      */         continue;
/*  768 */       IdentifierGenerator ig = ((IdentifierCollection)collection).getIdentifier()
/*  769 */         .createIdentifierGenerator(
/*  770 */         dialect, 
/*  771 */         defaultCatalog, 
/*  772 */         defaultSchema, 
/*  773 */         null);
/*      */ 
/*  776 */       if ((ig instanceof PersistentIdentifierGenerator)) {
/*  777 */         generators.put(((PersistentIdentifierGenerator)ig).generatorKey(), ig);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  783 */     return generators.values().iterator();
/*      */   }
/*      */ 
/*      */   public String[] generateDropSchemaScript(Dialect dialect)
/*      */     throws HibernateException
/*      */   {
/*  793 */     secondPassCompile();
/*      */ 
/*  795 */     String defaultCatalog = this.properties.getProperty("hibernate.default_catalog");
/*  796 */     String defaultSchema = this.properties.getProperty("hibernate.default_schema");
/*      */ 
/*  798 */     ArrayList script = new ArrayList(50);
/*      */ 
/*  801 */     ListIterator itr = this.auxiliaryDatabaseObjects.listIterator(this.auxiliaryDatabaseObjects.size());
/*  802 */     while (itr.hasPrevious()) {
/*  803 */       AuxiliaryDatabaseObject object = (AuxiliaryDatabaseObject)itr.previous();
/*  804 */       if (object.appliesToDialect(dialect)) {
/*  805 */         script.add(object.sqlDropString(dialect, defaultCatalog, defaultSchema));
/*      */       }
/*      */     }
/*      */ 
/*  809 */     if (dialect.dropConstraints()) {
/*  810 */       Iterator iter = getTableMappings();
/*  811 */       while (iter.hasNext()) {
/*  812 */         Table table = (Table)iter.next();
/*  813 */         if (table.isPhysicalTable()) {
/*  814 */           Iterator subIter = table.getForeignKeyIterator();
/*  815 */           while (subIter.hasNext()) {
/*  816 */             ForeignKey fk = (ForeignKey)subIter.next();
/*  817 */             if (fk.isPhysicalConstraint()) {
/*  818 */               script.add(
/*  819 */                 fk.sqlDropString(
/*  820 */                 dialect, 
/*  821 */                 defaultCatalog, 
/*  822 */                 defaultSchema));
/*      */             }
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  832 */     Iterator iter = getTableMappings();
/*  833 */     while (iter.hasNext())
/*      */     {
/*  835 */       Table table = (Table)iter.next();
/*  836 */       if (!table.isPhysicalTable())
/*      */       {
/*      */         continue;
/*      */       }
/*      */ 
/*  846 */       script.add(
/*  847 */         table.sqlDropString(
/*  848 */         dialect, 
/*  849 */         defaultCatalog, 
/*  850 */         defaultSchema));
/*      */     }
/*      */ 
/*  858 */     iter = iterateGenerators(dialect);
/*  859 */     while (iter.hasNext()) {
/*  860 */       String[] lines = ((PersistentIdentifierGenerator)iter.next()).sqlDropStrings(dialect);
/*  861 */       for (int i = 0; i < lines.length; i++) {
/*  862 */         script.add(lines[i]);
/*      */       }
/*      */     }
/*      */ 
/*  866 */     return ArrayHelper.toStringArray(script);
/*      */   }
/*      */ 
/*      */   public String[] generateSchemaCreationScript(Dialect dialect)
/*      */     throws HibernateException
/*      */   {
/*  875 */     secondPassCompile();
/*      */ 
/*  877 */     ArrayList script = new ArrayList(50);
/*  878 */     String defaultCatalog = this.properties.getProperty("hibernate.default_catalog");
/*  879 */     String defaultSchema = this.properties.getProperty("hibernate.default_schema");
/*      */ 
/*  881 */     Iterator iter = getTableMappings();
/*  882 */     while (iter.hasNext()) {
/*  883 */       Table table = (Table)iter.next();
/*  884 */       if (table.isPhysicalTable()) {
/*  885 */         script.add(
/*  886 */           table.sqlCreateString(
/*  887 */           dialect, 
/*  888 */           this.mapping, 
/*  889 */           defaultCatalog, 
/*  890 */           defaultSchema));
/*      */ 
/*  893 */         Iterator comments = table.sqlCommentStrings(dialect, defaultCatalog, defaultSchema);
/*  894 */         while (comments.hasNext()) {
/*  895 */           script.add(comments.next());
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  900 */     iter = getTableMappings();
/*  901 */     while (iter.hasNext()) {
/*  902 */       Table table = (Table)iter.next();
/*  903 */       if (!table.isPhysicalTable())
/*      */         continue;
/*  905 */       if (!dialect.supportsUniqueConstraintInCreateAlterTable()) {
/*  906 */         Iterator subIter = table.getUniqueKeyIterator();
/*  907 */         while (subIter.hasNext()) {
/*  908 */           UniqueKey uk = (UniqueKey)subIter.next();
/*  909 */           String constraintString = uk.sqlCreateString(dialect, this.mapping, defaultCatalog, defaultSchema);
/*  910 */           if (constraintString == null) continue; script.add(constraintString);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  915 */       Iterator subIter = table.getIndexIterator();
/*  916 */       while (subIter.hasNext()) {
/*  917 */         Index index = (Index)subIter.next();
/*  918 */         script.add(
/*  919 */           index.sqlCreateString(
/*  920 */           dialect, 
/*  921 */           this.mapping, 
/*  922 */           defaultCatalog, 
/*  923 */           defaultSchema));
/*      */       }
/*      */ 
/*  928 */       if (dialect.hasAlterTable()) {
/*  929 */         subIter = table.getForeignKeyIterator();
/*  930 */         while (subIter.hasNext()) {
/*  931 */           ForeignKey fk = (ForeignKey)subIter.next();
/*  932 */           if (fk.isPhysicalConstraint()) {
/*  933 */             script.add(
/*  934 */               fk.sqlCreateString(
/*  935 */               dialect, this.mapping, 
/*  936 */               defaultCatalog, 
/*  937 */               defaultSchema));
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  947 */     iter = iterateGenerators(dialect);
/*  948 */     while (iter.hasNext()) {
/*  949 */       String[] lines = ((PersistentIdentifierGenerator)iter.next()).sqlCreateStrings(dialect);
/*  950 */       for (int i = 0; i < lines.length; i++) {
/*  951 */         script.add(lines[i]);
/*      */       }
/*      */     }
/*      */ 
/*  955 */     Iterator itr = this.auxiliaryDatabaseObjects.iterator();
/*  956 */     while (itr.hasNext()) {
/*  957 */       AuxiliaryDatabaseObject object = (AuxiliaryDatabaseObject)itr.next();
/*  958 */       if (object.appliesToDialect(dialect)) {
/*  959 */         script.add(object.sqlCreateString(dialect, this.mapping, defaultCatalog, defaultSchema));
/*      */       }
/*      */     }
/*      */ 
/*  963 */     return ArrayHelper.toStringArray(script);
/*      */   }
/*      */ 
/*      */   public String[] generateSchemaUpdateScript(Dialect dialect, DatabaseMetadata databaseMetadata)
/*      */     throws HibernateException
/*      */   {
/*  973 */     secondPassCompile();
/*      */ 
/*  975 */     String defaultCatalog = this.properties.getProperty("hibernate.default_catalog");
/*  976 */     String defaultSchema = this.properties.getProperty("hibernate.default_schema");
/*      */ 
/*  978 */     ArrayList script = new ArrayList(50);
/*      */ 
/*  980 */     Iterator iter = getTableMappings();
/*  981 */     while (iter.hasNext()) {
/*  982 */       Table table = (Table)iter.next();
/*  983 */       if (!table.isPhysicalTable())
/*      */         continue;
/*  985 */       TableMetadata tableInfo = databaseMetadata.getTableMetadata(
/*  986 */         table.getName(), 
/*  987 */         table.getSchema() == null ? defaultSchema : table.getSchema(), 
/*  988 */         table.getCatalog() == null ? defaultCatalog : table.getCatalog(), 
/*  989 */         table.isQuoted());
/*      */ 
/*  992 */       if (tableInfo == null) {
/*  993 */         script.add(
/*  994 */           table.sqlCreateString(
/*  995 */           dialect, 
/*  996 */           this.mapping, 
/*  997 */           defaultCatalog, 
/*  998 */           defaultSchema));
/*      */       }
/*      */       else
/*      */       {
/* 1003 */         Iterator subiter = table.sqlAlterStrings(
/* 1004 */           dialect, 
/* 1005 */           this.mapping, 
/* 1006 */           tableInfo, 
/* 1007 */           defaultCatalog, 
/* 1008 */           defaultSchema);
/*      */ 
/* 1010 */         while (subiter.hasNext()) {
/* 1011 */           script.add(subiter.next());
/*      */         }
/*      */       }
/*      */ 
/* 1015 */       Iterator comments = table.sqlCommentStrings(dialect, defaultCatalog, defaultSchema);
/* 1016 */       while (comments.hasNext()) {
/* 1017 */         script.add(comments.next());
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1023 */     iter = getTableMappings();
/* 1024 */     while (iter.hasNext()) {
/* 1025 */       Table table = (Table)iter.next();
/* 1026 */       if (!table.isPhysicalTable())
/*      */         continue;
/* 1028 */       TableMetadata tableInfo = databaseMetadata.getTableMetadata(
/* 1029 */         table.getName(), 
/* 1030 */         table.getSchema(), 
/* 1031 */         table.getCatalog(), 
/* 1032 */         table.isQuoted());
/*      */ 
/* 1035 */       if (dialect.hasAlterTable()) {
/* 1036 */         Iterator subIter = table.getForeignKeyIterator();
/* 1037 */         while (subIter.hasNext()) {
/* 1038 */           ForeignKey fk = (ForeignKey)subIter.next();
/* 1039 */           if (fk.isPhysicalConstraint()) {
/* 1040 */             boolean create = (tableInfo == null) || (
/* 1041 */               (tableInfo.getForeignKeyMetadata(fk.getName()) == null) && (
/* 1043 */               (!(dialect instanceof MySQLDialect)) || 
/* 1044 */               (tableInfo.getIndexMetadata(fk.getName()) == null)));
/*      */ 
/* 1047 */             if (create) {
/* 1048 */               script.add(
/* 1049 */                 fk.sqlCreateString(
/* 1050 */                 dialect, 
/* 1051 */                 this.mapping, 
/* 1052 */                 defaultCatalog, 
/* 1053 */                 defaultSchema));
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1083 */     iter = iterateGenerators(dialect);
/* 1084 */     while (iter.hasNext()) {
/* 1085 */       PersistentIdentifierGenerator generator = (PersistentIdentifierGenerator)iter.next();
/* 1086 */       Object key = generator.generatorKey();
/* 1087 */       if ((!databaseMetadata.isSequence(key)) && (!databaseMetadata.isTable(key))) {
/* 1088 */         String[] lines = generator.sqlCreateStrings(dialect);
/* 1089 */         for (int i = 0; i < lines.length; i++) {
/* 1090 */           script.add(lines[i]);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1095 */     return ArrayHelper.toStringArray(script);
/*      */   }
/*      */ 
/*      */   public void validateSchema(Dialect dialect, DatabaseMetadata databaseMetadata) throws HibernateException
/*      */   {
/* 1100 */     secondPassCompile();
/*      */ 
/* 1102 */     String defaultCatalog = this.properties.getProperty("hibernate.default_catalog");
/* 1103 */     String defaultSchema = this.properties.getProperty("hibernate.default_schema");
/*      */ 
/* 1105 */     Iterator iter = getTableMappings();
/* 1106 */     while (iter.hasNext()) {
/* 1107 */       Table table = (Table)iter.next();
/* 1108 */       if (!table.isPhysicalTable()) {
/*      */         continue;
/*      */       }
/* 1111 */       TableMetadata tableInfo = databaseMetadata.getTableMetadata(
/* 1112 */         table.getName(), 
/* 1113 */         table.getSchema() == null ? defaultSchema : table.getSchema(), 
/* 1114 */         table.getCatalog() == null ? defaultCatalog : table.getCatalog(), 
/* 1115 */         table.isQuoted());
/* 1116 */       if (tableInfo == null) {
/* 1117 */         throw new HibernateException("Missing table: " + table.getName());
/*      */       }
/*      */ 
/* 1120 */       table.validateColumns(dialect, this.mapping, tableInfo);
/*      */     }
/*      */ 
/* 1126 */     iter = iterateGenerators(dialect);
/* 1127 */     while (iter.hasNext()) {
/* 1128 */       PersistentIdentifierGenerator generator = (PersistentIdentifierGenerator)iter.next();
/* 1129 */       Object key = generator.generatorKey();
/* 1130 */       if ((!databaseMetadata.isSequence(key)) && (!databaseMetadata.isTable(key)))
/* 1131 */         throw new HibernateException("Missing sequence or table: " + key);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void validate() throws MappingException
/*      */   {
/* 1137 */     Iterator iter = this.classes.values().iterator();
/* 1138 */     while (iter.hasNext()) {
/* 1139 */       ((PersistentClass)iter.next()).validate(this.mapping);
/*      */     }
/* 1141 */     iter = this.collections.values().iterator();
/* 1142 */     while (iter.hasNext())
/* 1143 */       ((org.hibernate.mapping.Collection)iter.next()).validate(this.mapping);
/*      */   }
/*      */ 
/*      */   public void buildMappings()
/*      */   {
/* 1152 */     secondPassCompile();
/*      */   }
/*      */ 
/*      */   protected void secondPassCompile() throws MappingException
/*      */   {
/* 1157 */     log.debug("processing extends queue");
/*      */ 
/* 1159 */     processExtendsQueue();
/*      */ 
/* 1161 */     log.debug("processing collection mappings");
/*      */ 
/* 1163 */     Iterator iter = this.secondPasses.iterator();
/* 1164 */     while (iter.hasNext()) {
/* 1165 */       SecondPass sp = (SecondPass)iter.next();
/* 1166 */       if (!(sp instanceof QuerySecondPass)) {
/* 1167 */         sp.doSecondPass(this.classes);
/* 1168 */         iter.remove();
/*      */       }
/*      */     }
/*      */ 
/* 1172 */     log.debug("processing native query and ResultSetMapping mappings");
/* 1173 */     iter = this.secondPasses.iterator();
/* 1174 */     while (iter.hasNext()) {
/* 1175 */       SecondPass sp = (SecondPass)iter.next();
/* 1176 */       sp.doSecondPass(this.classes);
/* 1177 */       iter.remove();
/*      */     }
/*      */ 
/* 1180 */     log.debug("processing association property references");
/*      */ 
/* 1182 */     iter = this.propertyReferences.iterator();
/* 1183 */     while (iter.hasNext()) {
/* 1184 */       Mappings.PropertyReference upr = (Mappings.PropertyReference)iter.next();
/*      */ 
/* 1186 */       PersistentClass clazz = getClassMapping(upr.referencedClass);
/* 1187 */       if (clazz == null) {
/* 1188 */         throw new MappingException(
/* 1189 */           "property-ref to unmapped class: " + 
/* 1190 */           upr.referencedClass);
/*      */       }
/*      */ 
/* 1194 */       Property prop = clazz.getReferencedProperty(upr.propertyName);
/* 1195 */       if (upr.unique) {
/* 1196 */         ((SimpleValue)prop.getValue()).setAlternateUniqueKey(true);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1202 */     log.debug("processing foreign key constraints");
/*      */ 
/* 1204 */     iter = getTableMappings();
/* 1205 */     Set done = new HashSet();
/* 1206 */     while (iter.hasNext())
/* 1207 */       secondPassCompileForeignKeys((Table)iter.next(), done);
/*      */   }
/*      */ 
/*      */   private void processExtendsQueue()
/*      */   {
/* 1222 */     org.dom4j.Document document = findPossibleExtends();
/* 1223 */     while (document != null) {
/* 1224 */       add(document);
/* 1225 */       document = findPossibleExtends();
/*      */     }
/*      */ 
/* 1228 */     if (this.extendsQueue.size() > 0)
/*      */     {
/* 1230 */       Iterator iterator = this.extendsQueue.keySet().iterator();
/* 1231 */       StringBuffer buf = new StringBuffer("Following superclasses referenced in extends not found: ");
/* 1232 */       while (iterator.hasNext()) {
/* 1233 */         ExtendsQueueEntry entry = (ExtendsQueueEntry)iterator.next();
/* 1234 */         buf.append(entry.getExplicitName());
/* 1235 */         if (entry.getMappingPackage() != null) {
/* 1236 */           buf.append("[").append(entry.getMappingPackage()).append("]");
/*      */         }
/* 1238 */         if (iterator.hasNext()) {
/* 1239 */           buf.append(",");
/*      */         }
/*      */       }
/* 1242 */       throw new MappingException(buf.toString());
/*      */     }
/*      */   }
/*      */ 
/*      */   protected org.dom4j.Document findPossibleExtends()
/*      */   {
/* 1251 */     Iterator iter = this.extendsQueue.keySet().iterator();
/* 1252 */     while (iter.hasNext()) {
/* 1253 */       ExtendsQueueEntry entry = (ExtendsQueueEntry)iter.next();
/* 1254 */       if (getClassMapping(entry.getExplicitName()) != null)
/*      */       {
/* 1256 */         iter.remove();
/* 1257 */         return entry.getDocument();
/*      */       }
/* 1259 */       if (getClassMapping(HbmBinder.getClassName(entry.getExplicitName(), entry.getMappingPackage())) == null)
/*      */         continue;
/* 1261 */       iter.remove();
/* 1262 */       return entry.getDocument();
/*      */     }
/*      */ 
/* 1265 */     return null;
/*      */   }
/*      */ 
/*      */   protected void secondPassCompileForeignKeys(Table table, Set done) throws MappingException
/*      */   {
/* 1270 */     table.createForeignKeys();
/*      */ 
/* 1272 */     Iterator iter = table.getForeignKeyIterator();
/* 1273 */     while (iter.hasNext())
/*      */     {
/* 1275 */       ForeignKey fk = (ForeignKey)iter.next();
/* 1276 */       if (!done.contains(fk)) {
/* 1277 */         done.add(fk);
/* 1278 */         String referencedEntityName = fk.getReferencedEntityName();
/* 1279 */         if (referencedEntityName == null) {
/* 1280 */           throw new MappingException(
/* 1281 */             "An association from the table " + 
/* 1282 */             fk.getTable().getName() + 
/* 1283 */             " does not specify the referenced entity");
/*      */         }
/*      */ 
/* 1286 */         if (log.isDebugEnabled()) {
/* 1287 */           log.debug("resolving reference to class: " + referencedEntityName);
/*      */         }
/* 1289 */         PersistentClass referencedClass = (PersistentClass)this.classes.get(referencedEntityName);
/* 1290 */         if (referencedClass == null) {
/* 1291 */           throw new MappingException(
/* 1292 */             "An association from the table " + 
/* 1293 */             fk.getTable().getName() + 
/* 1294 */             " refers to an unmapped class: " + 
/* 1295 */             referencedEntityName);
/*      */         }
/*      */ 
/* 1298 */         if (referencedClass.isJoinedSubclass()) {
/* 1299 */           secondPassCompileForeignKeys(referencedClass.getSuperclass().getTable(), done);
/*      */         }
/* 1301 */         fk.setReferencedTable(referencedClass.getTable());
/* 1302 */         fk.alignColumns();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public Map getNamedQueries()
/*      */   {
/* 1311 */     return this.namedQueries;
/*      */   }
/*      */ 
/*      */   public SessionFactory buildSessionFactory()
/*      */     throws HibernateException
/*      */   {
/* 1324 */     log.debug("Preparing to build session factory with filters : " + this.filterDefinitions);
/* 1325 */     secondPassCompile();
/* 1326 */     validate();
/* 1327 */     Environment.verifyProperties(this.properties);
/* 1328 */     Properties copy = new Properties();
/* 1329 */     copy.putAll(this.properties);
/* 1330 */     PropertiesHelper.resolvePlaceHolders(copy);
/* 1331 */     Settings settings = buildSettings(copy);
/*      */ 
/* 1333 */     return new SessionFactoryImpl(
/* 1334 */       this, 
/* 1335 */       this.mapping, 
/* 1336 */       settings, 
/* 1337 */       getInitializedEventListeners(), 
/* 1338 */       this.sessionFactoryObserver);
/*      */   }
/*      */ 
/*      */   private EventListeners getInitializedEventListeners()
/*      */   {
/* 1343 */     EventListeners result = (EventListeners)this.eventListeners.shallowCopy();
/* 1344 */     result.initializeListeners(this);
/* 1345 */     return result;
/*      */   }
/*      */ 
/*      */   public Interceptor getInterceptor()
/*      */   {
/* 1352 */     return this.interceptor;
/*      */   }
/*      */ 
/*      */   public Properties getProperties()
/*      */   {
/* 1359 */     return this.properties;
/*      */   }
/*      */ 
/*      */   public Configuration setInterceptor(Interceptor interceptor)
/*      */   {
/* 1366 */     this.interceptor = interceptor;
/* 1367 */     return this;
/*      */   }
/*      */ 
/*      */   public Configuration setProperties(Properties properties)
/*      */   {
/* 1374 */     this.properties = properties;
/* 1375 */     return this;
/*      */   }
/*      */ 
/*      */   public Configuration addProperties(Properties extraProperties)
/*      */   {
/* 1382 */     this.properties.putAll(extraProperties);
/* 1383 */     return this;
/*      */   }
/*      */ 
/*      */   public Configuration mergeProperties(Properties properties)
/*      */   {
/* 1395 */     Iterator itr = properties.entrySet().iterator();
/* 1396 */     while (itr.hasNext()) {
/* 1397 */       Map.Entry entry = (Map.Entry)itr.next();
/* 1398 */       if (this.properties.containsKey(entry.getKey())) {
/*      */         continue;
/*      */       }
/* 1401 */       this.properties.setProperty((String)entry.getKey(), (String)entry.getValue());
/*      */     }
/* 1403 */     return this;
/*      */   }
/*      */ 
/*      */   public Configuration setProperty(String propertyName, String value)
/*      */   {
/* 1410 */     this.properties.setProperty(propertyName, value);
/* 1411 */     return this;
/*      */   }
/*      */ 
/*      */   public String getProperty(String propertyName)
/*      */   {
/* 1418 */     return this.properties.getProperty(propertyName);
/*      */   }
/*      */ 
/*      */   private void addProperties(Element parent) {
/* 1422 */     Iterator iter = parent.elementIterator("property");
/* 1423 */     while (iter.hasNext()) {
/* 1424 */       Element node = (Element)iter.next();
/* 1425 */       String name = node.attributeValue("name");
/* 1426 */       String value = node.getText().trim();
/* 1427 */       log.debug(name + "=" + value);
/* 1428 */       this.properties.setProperty(name, value);
/* 1429 */       if (!name.startsWith("hibernate")) {
/* 1430 */         this.properties.setProperty("hibernate." + name, value);
/*      */       }
/*      */     }
/* 1433 */     Environment.verifyProperties(this.properties);
/*      */   }
/*      */ 
/*      */   protected InputStream getConfigurationInputStream(String resource)
/*      */     throws HibernateException
/*      */   {
/* 1443 */     log.info("Configuration resource: " + resource);
/*      */ 
/* 1445 */     return ConfigHelper.getResourceAsStream(resource);
/*      */   }
/*      */ 
/*      */   public Configuration configure()
/*      */     throws HibernateException
/*      */   {
/* 1454 */     configure("/hibernate.cfg.xml");
/* 1455 */     return this;
/*      */   }
/*      */ 
/*      */   public Configuration configure(String resource)
/*      */     throws HibernateException
/*      */   {
/* 1466 */     log.info("configuring from resource: " + resource);
/* 1467 */     InputStream stream = getConfigurationInputStream(resource);
/* 1468 */     return doConfigure(stream, resource);
/*      */   }
/*      */ 
/*      */   public Configuration configure(URL url)
/*      */     throws HibernateException
/*      */   {
/* 1481 */     log.info("configuring from url: " + url.toString());
/*      */     try {
/* 1483 */       return doConfigure(url.openStream(), url.toString());
/*      */     } catch (IOException ioe) {
	/* 1486 */     throw new HibernateException("could not configure from URL: " + url, ioe);
/*      */     }
/*      */   }
/*      */ 
/*      */   public Configuration configure(File configFile)
/*      */     throws HibernateException
/*      */   {
/* 1500 */     log.info("configuring from file: " + configFile.getName());
/*      */     try {
/* 1502 */       return doConfigure(new FileInputStream(configFile), configFile.toString());
/*      */     } catch (FileNotFoundException fnfe) {
	/* 1505 */     throw new HibernateException("could not find file: " + configFile, fnfe); } 
/*      */     }
/*      */   // ERROR //
/*      */   protected Configuration doConfigure(InputStream stream, String resourceName) throws HibernateException { // Byte code:
	return null;
}
/*      */     //   0: new 95	java/util/ArrayList
/*      */     //   3: dup
/*      */     //   4: invokespecial 97	java/util/ArrayList:<init>	()V
/*      */     //   7: astore 4
/*      */     //   9: aload_0
/*      */     //   10: getfield 91	org/hibernate/cfg/Configuration:xmlHelper	Lorg/hibernate/util/XMLHelper;
/*      */     //   13: aload_2
/*      */     //   14: aload 4
/*      */     //   16: aload_0
/*      */     //   17: getfield 120	org/hibernate/cfg/Configuration:entityResolver	Lorg/xml/sax/EntityResolver;
/*      */     //   20: invokevirtual 247	org/hibernate/util/XMLHelper:createSAXReader	(Ljava/lang/String;Ljava/util/List;Lorg/xml/sax/EntityResolver;)Lorg/dom4j/io/SAXReader;
/*      */     //   23: new 424	org/xml/sax/InputSource
/*      */     //   26: dup
/*      */     //   27: aload_1
/*      */     //   28: invokespecial 426	org/xml/sax/InputSource:<init>	(Ljava/io/InputStream;)V
/*      */     //   31: invokevirtual 429	org/dom4j/io/SAXReader:read	(Lorg/xml/sax/InputSource;)Lorg/dom4j/Document;
/*      */     //   34: astore_3
/*      */     //   35: aload 4
/*      */     //   37: invokeinterface 257 1 0
/*      */     //   42: ifeq +95 -> 137
/*      */     //   45: new 203	org/hibernate/MappingException
/*      */     //   48: dup
/*      */     //   49: ldc_w 1139
/*      */     //   52: aload 4
/*      */     //   54: iconst_0
/*      */     //   55: invokeinterface 265 2 0
/*      */     //   60: checkcast 268	java/lang/Throwable
/*      */     //   63: invokespecial 332	org/hibernate/MappingException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
/*      */     //   66: athrow
/*      */     //   67: astore 4
/*      */     //   69: new 656	org/hibernate/HibernateException
/*      */     //   72: dup
/*      */     //   73: new 214	java/lang/StringBuilder
/*      */     //   76: dup
/*      */     //   77: ldc_w 1141
/*      */     //   80: invokespecial 218	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   83: aload_2
/*      */     //   84: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   87: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   90: aload 4
/*      */     //   92: invokespecial 1132	org/hibernate/HibernateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
/*      */     //   95: athrow
/*      */     //   96: astore 5
/*      */     //   98: aload_1
/*      */     //   99: invokevirtual 432	java/io/InputStream:close	()V
/*      */     //   102: goto +32 -> 134
/*      */     //   105: astore 6
/*      */     //   107: getstatic 56	org/hibernate/cfg/Configuration:log	Lorg/slf4j/Logger;
/*      */     //   110: new 214	java/lang/StringBuilder
/*      */     //   113: dup
/*      */     //   114: ldc_w 1143
/*      */     //   117: invokespecial 218	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   120: aload_2
/*      */     //   121: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   124: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   127: aload 6
/*      */     //   129: invokeinterface 324 3 0
/*      */     //   134: aload 5
/*      */     //   136: athrow
/*      */     //   137: aload_1
/*      */     //   138: invokevirtual 432	java/io/InputStream:close	()V
/*      */     //   141: goto +32 -> 173
/*      */     //   144: astore 6
/*      */     //   146: getstatic 56	org/hibernate/cfg/Configuration:log	Lorg/slf4j/Logger;
/*      */     //   149: new 214	java/lang/StringBuilder
/*      */     //   152: dup
/*      */     //   153: ldc_w 1143
/*      */     //   156: invokespecial 218	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
/*      */     //   159: aload_2
/*      */     //   160: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   163: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   166: aload 6
/*      */     //   168: invokeinterface 324 3 0
/*      */     //   173: aload_0
/*      */     //   174: aload_3
/*      */     //   175: invokevirtual 1145	org/hibernate/cfg/Configuration:doConfigure	(Lorg/dom4j/Document;)Lorg/hibernate/cfg/Configuration;
/*      */     //   178: areturn
/*      */     //
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   0	67	67	org/dom4j/DocumentException
/*      */     //   0	96	96	finally
/*      */     //   98	102	105	java/io/IOException
/*      */     //   137	141	144	java/io/IOException } 
/* 1562 */   public Configuration configure(org.w3c.dom.Document document) throws HibernateException { log.info("configuring from XML document");
/* 1563 */     return doConfigure(this.xmlHelper.createDOMReader().read(document)); }
/*      */ 
/*      */   protected Configuration doConfigure(org.dom4j.Document doc)
/*      */     throws HibernateException
/*      */   {
/* 1568 */     Element sfNode = doc.getRootElement().element("session-factory");
/* 1569 */     String name = sfNode.attributeValue("name");
/* 1570 */     if (name != null) {
/* 1571 */       this.properties.setProperty("hibernate.session_factory_name", name);
/*      */     }
/* 1573 */     addProperties(sfNode);
/* 1574 */     parseSessionFactory(sfNode, name);
/*      */ 
/* 1576 */     Element secNode = doc.getRootElement().element("security");
/* 1577 */     if (secNode != null) {
/* 1578 */       parseSecurity(secNode);
/*      */     }
/*      */ 
/* 1581 */     log.info("Configured SessionFactory: " + name);
/* 1582 */     log.debug("properties: " + this.properties);
/*      */ 
/* 1584 */     return this;
/*      */   }
/*      */ 
/*      */   private void parseSessionFactory(Element sfNode, String name)
/*      */   {
/* 1590 */     Iterator elements = sfNode.elementIterator();
/* 1591 */     while (elements.hasNext()) {
/* 1592 */       Element subelement = (Element)elements.next();
/* 1593 */       String subelementName = subelement.getName();
/* 1594 */       if ("mapping".equals(subelementName)) {
/* 1595 */         parseMappingElement(subelement, name);
/*      */       }
/* 1597 */       else if ("class-cache".equals(subelementName)) {
/* 1598 */         String className = subelement.attributeValue("class");
/* 1599 */         Attribute regionNode = subelement.attribute("region");
/* 1600 */         String region = regionNode == null ? className : regionNode.getValue();
/* 1601 */         boolean includeLazy = !"non-lazy".equals(subelement.attributeValue("include"));
/* 1602 */         setCacheConcurrencyStrategy(className, subelement.attributeValue("usage"), region, includeLazy);
/*      */       }
/* 1604 */       else if ("collection-cache".equals(subelementName)) {
/* 1605 */         String role = subelement.attributeValue("collection");
/* 1606 */         Attribute regionNode = subelement.attribute("region");
/* 1607 */         String region = regionNode == null ? role : regionNode.getValue();
/* 1608 */         setCollectionCacheConcurrencyStrategy(role, subelement.attributeValue("usage"), region);
/*      */       }
/* 1610 */       else if ("listener".equals(subelementName)) {
/* 1611 */         parseListener(subelement);
/*      */       }
/* 1613 */       else if ("event".equals(subelementName)) {
/* 1614 */         parseEvent(subelement);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void parseMappingElement(Element subelement, String name) {
/* 1620 */     Attribute rsrc = subelement.attribute("resource");
/* 1621 */     Attribute file = subelement.attribute("file");
/* 1622 */     Attribute jar = subelement.attribute("jar");
/* 1623 */     Attribute pkg = subelement.attribute("package");
/* 1624 */     Attribute clazz = subelement.attribute("class");
/* 1625 */     if (rsrc != null) {
/* 1626 */       log.debug(name + "<-" + rsrc);
/* 1627 */       addResource(rsrc.getValue());
/*      */     }
/* 1629 */     else if (jar != null) {
/* 1630 */       log.debug(name + "<-" + jar);
/* 1631 */       addJar(new File(jar.getValue()));
/*      */     } else {
/* 1633 */       if (pkg != null) {
/* 1634 */         throw new MappingException(
/* 1635 */           "An AnnotationConfiguration instance is required to use <mapping package=\"" + 
/* 1636 */           pkg.getValue() + "\"/>");
/*      */       }
/*      */ 
/* 1639 */       if (clazz != null) {
/* 1640 */         throw new MappingException(
/* 1641 */           "An AnnotationConfiguration instance is required to use <mapping class=\"" + 
/* 1642 */           clazz.getValue() + "\"/>");
/*      */       }
/*      */ 
/* 1646 */       if (file == null) {
/* 1647 */         throw new MappingException(
/* 1648 */           "<mapping> element in configuration specifies no attributes");
/*      */       }
/*      */ 
/* 1651 */       log.debug(name + "<-" + file);
/* 1652 */       addFile(file.getValue());
/*      */     }
/*      */   }
/*      */ 
/*      */   private void parseSecurity(Element secNode) {
/* 1657 */     String contextId = secNode.attributeValue("context");
/* 1658 */     setProperty("hibernate.jacc_context_id", contextId);
/* 1659 */     log.info("JACC contextID: " + contextId);
/* 1660 */     JACCConfiguration jcfg = new JACCConfiguration(contextId);
/* 1661 */     Iterator grantElements = secNode.elementIterator();
/* 1662 */     while (grantElements.hasNext()) {
/* 1663 */       Element grantElement = (Element)grantElements.next();
/* 1664 */       String elementName = grantElement.getName();
/* 1665 */       if ("grant".equals(elementName))
/* 1666 */         jcfg.addPermission(
/* 1667 */           grantElement.attributeValue("role"), 
/* 1668 */           grantElement.attributeValue("entity-name"), 
/* 1669 */           grantElement.attributeValue("actions"));
/*      */     }
/*      */   }
/*      */ 
/*      */   private void parseEvent(Element element)
/*      */   {
/* 1676 */     String type = element.attributeValue("type");
/* 1677 */     List listeners = element.elements();
/* 1678 */     String[] listenerClasses = new String[listeners.size()];
/* 1679 */     for (int i = 0; i < listeners.size(); i++) {
/* 1680 */       listenerClasses[i] = ((Element)listeners.get(i)).attributeValue("class");
/*      */     }
/* 1682 */     log.debug("Event listeners: " + type + "=" + StringHelper.toString(listenerClasses));
/* 1683 */     setListeners(type, listenerClasses);
/*      */   }
/*      */ 
/*      */   private void parseListener(Element element) {
/* 1687 */     String type = element.attributeValue("type");
/* 1688 */     if (type == null) {
/* 1689 */       throw new MappingException("No type specified for listener");
/*      */     }
/* 1691 */     String impl = element.attributeValue("class");
/* 1692 */     log.debug("Event listener: " + type + "=" + impl);
/* 1693 */     setListeners(type, new String[] { impl });
/*      */   }
/*      */ 
/*      */   public void setListener(String type, String listener) {
/* 1697 */     String[] listeners = (String[])null;
/* 1698 */     if (listener != null) {
/* 1699 */       listeners = (String[])Array.newInstance(String.class, 1);
/* 1700 */       listeners[0] = listener;
/*      */     }
/* 1702 */     setListeners(type, listeners);
/*      */   }
/*      */ 
/*      */   public void setListeners(String type, String[] listenerClasses) {
/* 1706 */     Object[] listeners = (Object[])null;
/* 1707 */     if (listenerClasses != null) {
/* 1708 */       listeners = (Object[])Array.newInstance(this.eventListeners.getListenerClassFor(type), listenerClasses.length);
/* 1709 */       for (int i = 0; i < listeners.length; i++) {
/*      */         try {
/* 1711 */           listeners[i] = ReflectHelper.classForName(listenerClasses[i]).newInstance();
/*      */         }
/*      */         catch (Exception e) {
/* 1714 */           throw new MappingException(
/* 1715 */             "Unable to instantiate specified event (" + type + ") listener class: " + listenerClasses[i], 
/* 1716 */             e);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1721 */     setListeners(type, listeners);
/*      */   }
/*      */ 
/*      */   public void setListener(String type, Object listener) {
/* 1725 */     Object[] listeners = (Object[])null;
/* 1726 */     if (listener != null) {
/* 1727 */       listeners = (Object[])Array.newInstance(this.eventListeners.getListenerClassFor(type), 1);
/* 1728 */       listeners[0] = listener;
/*      */     }
/* 1730 */     setListeners(type, listeners);
/*      */   }
/*      */ 
/*      */   public void setListeners(String type, Object[] listeners) {
/* 1734 */     if ("auto-flush".equals(type)) {
/* 1735 */       if (listeners == null) {
/* 1736 */         this.eventListeners.setAutoFlushEventListeners(new AutoFlushEventListener[0]);
/*      */       }
/*      */       else {
/* 1739 */         this.eventListeners.setAutoFlushEventListeners((AutoFlushEventListener[])listeners);
/*      */       }
/*      */     }
/* 1742 */     else if ("merge".equals(type)) {
/* 1743 */       if (listeners == null) {
/* 1744 */         this.eventListeners.setMergeEventListeners(new MergeEventListener[0]);
/*      */       }
/*      */       else {
/* 1747 */         this.eventListeners.setMergeEventListeners((MergeEventListener[])listeners);
/*      */       }
/*      */     }
/* 1750 */     else if ("create".equals(type)) {
/* 1751 */       if (listeners == null) {
/* 1752 */         this.eventListeners.setPersistEventListeners(new PersistEventListener[0]);
/*      */       }
/*      */       else {
/* 1755 */         this.eventListeners.setPersistEventListeners((PersistEventListener[])listeners);
/*      */       }
/*      */     }
/* 1758 */     else if ("create-onflush".equals(type)) {
/* 1759 */       if (listeners == null) {
/* 1760 */         this.eventListeners.setPersistOnFlushEventListeners(new PersistEventListener[0]);
/*      */       }
/*      */       else {
/* 1763 */         this.eventListeners.setPersistOnFlushEventListeners((PersistEventListener[])listeners);
/*      */       }
/*      */     }
/* 1766 */     else if ("delete".equals(type)) {
/* 1767 */       if (listeners == null) {
/* 1768 */         this.eventListeners.setDeleteEventListeners(new DeleteEventListener[0]);
/*      */       }
/*      */       else {
/* 1771 */         this.eventListeners.setDeleteEventListeners((DeleteEventListener[])listeners);
/*      */       }
/*      */     }
/* 1774 */     else if ("dirty-check".equals(type)) {
/* 1775 */       if (listeners == null) {
/* 1776 */         this.eventListeners.setDirtyCheckEventListeners(new DirtyCheckEventListener[0]);
/*      */       }
/*      */       else {
/* 1779 */         this.eventListeners.setDirtyCheckEventListeners((DirtyCheckEventListener[])listeners);
/*      */       }
/*      */     }
/* 1782 */     else if ("evict".equals(type)) {
/* 1783 */       if (listeners == null) {
/* 1784 */         this.eventListeners.setEvictEventListeners(new EvictEventListener[0]);
/*      */       }
/*      */       else {
/* 1787 */         this.eventListeners.setEvictEventListeners((EvictEventListener[])listeners);
/*      */       }
/*      */     }
/* 1790 */     else if ("flush".equals(type)) {
/* 1791 */       if (listeners == null) {
/* 1792 */         this.eventListeners.setFlushEventListeners(new FlushEventListener[0]);
/*      */       }
/*      */       else {
/* 1795 */         this.eventListeners.setFlushEventListeners((FlushEventListener[])listeners);
/*      */       }
/*      */     }
/* 1798 */     else if ("flush-entity".equals(type)) {
/* 1799 */       if (listeners == null) {
/* 1800 */         this.eventListeners.setFlushEntityEventListeners(new FlushEntityEventListener[0]);
/*      */       }
/*      */       else {
/* 1803 */         this.eventListeners.setFlushEntityEventListeners((FlushEntityEventListener[])listeners);
/*      */       }
/*      */     }
/* 1806 */     else if ("load".equals(type)) {
/* 1807 */       if (listeners == null) {
/* 1808 */         this.eventListeners.setLoadEventListeners(new LoadEventListener[0]);
/*      */       }
/*      */       else {
/* 1811 */         this.eventListeners.setLoadEventListeners((LoadEventListener[])listeners);
/*      */       }
/*      */     }
/* 1814 */     else if ("load-collection".equals(type)) {
/* 1815 */       if (listeners == null) {
/* 1816 */         this.eventListeners.setInitializeCollectionEventListeners(
/* 1817 */           new InitializeCollectionEventListener[0]);
/*      */       }
/*      */       else
/*      */       {
/* 1821 */         this.eventListeners.setInitializeCollectionEventListeners(
/* 1822 */           (InitializeCollectionEventListener[])listeners);
/*      */       }
/*      */ 
/*      */     }
/* 1826 */     else if ("lock".equals(type)) {
/* 1827 */       if (listeners == null) {
/* 1828 */         this.eventListeners.setLockEventListeners(new LockEventListener[0]);
/*      */       }
/*      */       else {
/* 1831 */         this.eventListeners.setLockEventListeners((LockEventListener[])listeners);
/*      */       }
/*      */     }
/* 1834 */     else if ("refresh".equals(type)) {
/* 1835 */       if (listeners == null) {
/* 1836 */         this.eventListeners.setRefreshEventListeners(new RefreshEventListener[0]);
/*      */       }
/*      */       else {
/* 1839 */         this.eventListeners.setRefreshEventListeners((RefreshEventListener[])listeners);
/*      */       }
/*      */     }
/* 1842 */     else if ("replicate".equals(type)) {
/* 1843 */       if (listeners == null) {
/* 1844 */         this.eventListeners.setReplicateEventListeners(new ReplicateEventListener[0]);
/*      */       }
/*      */       else {
/* 1847 */         this.eventListeners.setReplicateEventListeners((ReplicateEventListener[])listeners);
/*      */       }
/*      */     }
/* 1850 */     else if ("save-update".equals(type)) {
/* 1851 */       if (listeners == null) {
/* 1852 */         this.eventListeners.setSaveOrUpdateEventListeners(new SaveOrUpdateEventListener[0]);
/*      */       }
/*      */       else {
/* 1855 */         this.eventListeners.setSaveOrUpdateEventListeners((SaveOrUpdateEventListener[])listeners);
/*      */       }
/*      */     }
/* 1858 */     else if ("save".equals(type)) {
/* 1859 */       if (listeners == null) {
/* 1860 */         this.eventListeners.setSaveEventListeners(new SaveOrUpdateEventListener[0]);
/*      */       }
/*      */       else {
/* 1863 */         this.eventListeners.setSaveEventListeners((SaveOrUpdateEventListener[])listeners);
/*      */       }
/*      */     }
/* 1866 */     else if ("update".equals(type)) {
/* 1867 */       if (listeners == null) {
/* 1868 */         this.eventListeners.setUpdateEventListeners(new SaveOrUpdateEventListener[0]);
/*      */       }
/*      */       else {
/* 1871 */         this.eventListeners.setUpdateEventListeners((SaveOrUpdateEventListener[])listeners);
/*      */       }
/*      */     }
/* 1874 */     else if ("pre-load".equals(type)) {
/* 1875 */       if (listeners == null) {
/* 1876 */         this.eventListeners.setPreLoadEventListeners(new PreLoadEventListener[0]);
/*      */       }
/*      */       else {
/* 1879 */         this.eventListeners.setPreLoadEventListeners((PreLoadEventListener[])listeners);
/*      */       }
/*      */     }
/* 1882 */     else if ("pre-update".equals(type)) {
/* 1883 */       if (listeners == null) {
/* 1884 */         this.eventListeners.setPreUpdateEventListeners(new PreUpdateEventListener[0]);
/*      */       }
/*      */       else {
/* 1887 */         this.eventListeners.setPreUpdateEventListeners((PreUpdateEventListener[])listeners);
/*      */       }
/*      */     }
/* 1890 */     else if ("pre-delete".equals(type)) {
/* 1891 */       if (listeners == null) {
/* 1892 */         this.eventListeners.setPreDeleteEventListeners(new PreDeleteEventListener[0]);
/*      */       }
/*      */       else {
/* 1895 */         this.eventListeners.setPreDeleteEventListeners((PreDeleteEventListener[])listeners);
/*      */       }
/*      */     }
/* 1898 */     else if ("pre-insert".equals(type)) {
/* 1899 */       if (listeners == null) {
/* 1900 */         this.eventListeners.setPreInsertEventListeners(new PreInsertEventListener[0]);
/*      */       }
/*      */       else {
/* 1903 */         this.eventListeners.setPreInsertEventListeners((PreInsertEventListener[])listeners);
/*      */       }
/*      */     }
/* 1906 */     else if ("pre-collection-recreate".equals(type)) {
/* 1907 */       if (listeners == null) {
/* 1908 */         this.eventListeners.setPreCollectionRecreateEventListeners(new PreCollectionRecreateEventListener[0]);
/*      */       }
/*      */       else {
/* 1911 */         this.eventListeners.setPreCollectionRecreateEventListeners((PreCollectionRecreateEventListener[])listeners);
/*      */       }
/*      */     }
/* 1914 */     else if ("pre-collection-remove".equals(type)) {
/* 1915 */       if (listeners == null) {
/* 1916 */         this.eventListeners.setPreCollectionRemoveEventListeners(new PreCollectionRemoveEventListener[0]);
/*      */       }
/*      */       else {
/* 1919 */         this.eventListeners.setPreCollectionRemoveEventListeners((PreCollectionRemoveEventListener[])listeners);
/*      */       }
/*      */     }
/* 1922 */     else if ("pre-collection-update".equals(type)) {
/* 1923 */       if (listeners == null) {
/* 1924 */         this.eventListeners.setPreCollectionUpdateEventListeners(new PreCollectionUpdateEventListener[0]);
/*      */       }
/*      */       else {
/* 1927 */         this.eventListeners.setPreCollectionUpdateEventListeners((PreCollectionUpdateEventListener[])listeners);
/*      */       }
/*      */     }
/* 1930 */     else if ("post-load".equals(type)) {
/* 1931 */       if (listeners == null) {
/* 1932 */         this.eventListeners.setPostLoadEventListeners(new PostLoadEventListener[0]);
/*      */       }
/*      */       else {
/* 1935 */         this.eventListeners.setPostLoadEventListeners((PostLoadEventListener[])listeners);
/*      */       }
/*      */     }
/* 1938 */     else if ("post-update".equals(type)) {
/* 1939 */       if (listeners == null) {
/* 1940 */         this.eventListeners.setPostUpdateEventListeners(new PostUpdateEventListener[0]);
/*      */       }
/*      */       else {
/* 1943 */         this.eventListeners.setPostUpdateEventListeners((PostUpdateEventListener[])listeners);
/*      */       }
/*      */     }
/* 1946 */     else if ("post-delete".equals(type)) {
/* 1947 */       if (listeners == null) {
/* 1948 */         this.eventListeners.setPostDeleteEventListeners(new PostDeleteEventListener[0]);
/*      */       }
/*      */       else {
/* 1951 */         this.eventListeners.setPostDeleteEventListeners((PostDeleteEventListener[])listeners);
/*      */       }
/*      */     }
/* 1954 */     else if ("post-insert".equals(type)) {
/* 1955 */       if (listeners == null) {
/* 1956 */         this.eventListeners.setPostInsertEventListeners(new PostInsertEventListener[0]);
/*      */       }
/*      */       else {
/* 1959 */         this.eventListeners.setPostInsertEventListeners((PostInsertEventListener[])listeners);
/*      */       }
/*      */     }
/* 1962 */     else if ("post-commit-update".equals(type)) {
/* 1963 */       if (listeners == null) {
/* 1964 */         this.eventListeners.setPostCommitUpdateEventListeners(
/* 1965 */           new PostUpdateEventListener[0]);
/*      */       }
/*      */       else
/*      */       {
/* 1969 */         this.eventListeners.setPostCommitUpdateEventListeners((PostUpdateEventListener[])listeners);
/*      */       }
/*      */     }
/* 1972 */     else if ("post-commit-delete".equals(type)) {
/* 1973 */       if (listeners == null) {
/* 1974 */         this.eventListeners.setPostCommitDeleteEventListeners(
/* 1975 */           new PostDeleteEventListener[0]);
/*      */       }
/*      */       else
/*      */       {
/* 1979 */         this.eventListeners.setPostCommitDeleteEventListeners((PostDeleteEventListener[])listeners);
/*      */       }
/*      */     }
/* 1982 */     else if ("post-commit-insert".equals(type)) {
/* 1983 */       if (listeners == null) {
/* 1984 */         this.eventListeners.setPostCommitInsertEventListeners(
/* 1985 */           new PostInsertEventListener[0]);
/*      */       }
/*      */       else
/*      */       {
/* 1989 */         this.eventListeners.setPostCommitInsertEventListeners((PostInsertEventListener[])listeners);
/*      */       }
/*      */     }
/* 1992 */     else if ("post-collection-recreate".equals(type)) {
/* 1993 */       if (listeners == null) {
/* 1994 */         this.eventListeners.setPostCollectionRecreateEventListeners(new PostCollectionRecreateEventListener[0]);
/*      */       }
/*      */       else {
/* 1997 */         this.eventListeners.setPostCollectionRecreateEventListeners((PostCollectionRecreateEventListener[])listeners);
/*      */       }
/*      */     }
/* 2000 */     else if ("post-collection-remove".equals(type)) {
/* 2001 */       if (listeners == null) {
/* 2002 */         this.eventListeners.setPostCollectionRemoveEventListeners(new PostCollectionRemoveEventListener[0]);
/*      */       }
/*      */       else {
/* 2005 */         this.eventListeners.setPostCollectionRemoveEventListeners((PostCollectionRemoveEventListener[])listeners);
/*      */       }
/*      */     }
/* 2008 */     else if ("post-collection-update".equals(type)) {
/* 2009 */       if (listeners == null) {
/* 2010 */         this.eventListeners.setPostCollectionUpdateEventListeners(new PostCollectionUpdateEventListener[0]);
/*      */       }
/*      */       else {
/* 2013 */         this.eventListeners.setPostCollectionUpdateEventListeners((PostCollectionUpdateEventListener[])listeners);
/*      */       }
/*      */     }
/*      */     else
/* 2017 */       throw new MappingException("Unrecognized listener type [" + type + "]");
/*      */   }
/*      */ 
/*      */   public EventListeners getEventListeners()
/*      */   {
/* 2022 */     return this.eventListeners;
/*      */   }
/*      */ 
/*      */   RootClass getRootClassMapping(String clazz) throws MappingException {
/*      */     try {
/* 2027 */       return (RootClass)getClassMapping(clazz);
/*      */     } catch (ClassCastException cce) {
/*      */     }
/* 2030 */     throw new MappingException("You may only specify a cache for root <class> mappings");
/*      */   }
/*      */ 
/*      */   public Configuration setCacheConcurrencyStrategy(String clazz, String concurrencyStrategy)
/*      */     throws MappingException
/*      */   {
/* 2044 */     setCacheConcurrencyStrategy(clazz, concurrencyStrategy, clazz);
/* 2045 */     return this;
/*      */   }
/*      */ 
/*      */   public void setCacheConcurrencyStrategy(String clazz, String concurrencyStrategy, String region) throws MappingException
/*      */   {
/* 2050 */     setCacheConcurrencyStrategy(clazz, concurrencyStrategy, region, true);
/*      */   }
/*      */ 
/*      */   void setCacheConcurrencyStrategy(String clazz, String concurrencyStrategy, String region, boolean includeLazy) throws MappingException
/*      */   {
/* 2055 */     RootClass rootClass = getRootClassMapping(clazz);
/* 2056 */     if (rootClass == null) {
/* 2057 */       throw new MappingException("Cannot cache an unknown entity: " + clazz);
/*      */     }
/* 2059 */     rootClass.setCacheConcurrencyStrategy(concurrencyStrategy);
/* 2060 */     rootClass.setCacheRegionName(region);
/* 2061 */     rootClass.setLazyPropertiesCacheable(includeLazy);
/*      */   }
/*      */ 
/*      */   public Configuration setCollectionCacheConcurrencyStrategy(String collectionRole, String concurrencyStrategy)
/*      */     throws MappingException
/*      */   {
/* 2074 */     setCollectionCacheConcurrencyStrategy(collectionRole, concurrencyStrategy, collectionRole);
/* 2075 */     return this;
/*      */   }
/*      */ 
/*      */   public void setCollectionCacheConcurrencyStrategy(String collectionRole, String concurrencyStrategy, String region) throws MappingException
/*      */   {
/* 2080 */     org.hibernate.mapping.Collection collection = getCollectionMapping(collectionRole);
/* 2081 */     if (collection == null) {
/* 2082 */       throw new MappingException("Cannot cache an unknown collection: " + collectionRole);
/*      */     }
/* 2084 */     collection.setCacheConcurrencyStrategy(concurrencyStrategy);
/* 2085 */     collection.setCacheRegionName(region);
/*      */   }
/*      */ 
/*      */   public Map getImports()
/*      */   {
/* 2094 */     return this.imports;
/*      */   }
/*      */ 
/*      */   public Settings buildSettings()
/*      */     throws HibernateException
/*      */   {
/* 2101 */     Properties clone = (Properties)this.properties.clone();
/* 2102 */     PropertiesHelper.resolvePlaceHolders(clone);
/* 2103 */     return this.settingsFactory.buildSettings(clone);
/*      */   }
/*      */ 
/*      */   public Settings buildSettings(Properties props) throws HibernateException {
/* 2107 */     return this.settingsFactory.buildSettings(props);
/*      */   }
/*      */ 
/*      */   public Map getNamedSQLQueries() {
/* 2111 */     return this.namedSqlQueries;
/*      */   }
/*      */ 
/*      */   public Map getSqlResultSetMappings() {
/* 2115 */     return this.sqlResultSetMappings;
/*      */   }
/*      */ 
/*      */   public NamingStrategy getNamingStrategy()
/*      */   {
/* 2122 */     return this.namingStrategy;
/*      */   }
/*      */ 
/*      */   public Configuration setNamingStrategy(NamingStrategy namingStrategy)
/*      */   {
/* 2131 */     this.namingStrategy = namingStrategy;
/* 2132 */     return this;
/*      */   }
/*      */ 
/*      */   public Mapping buildMapping() {
/* 2136 */     return new Mapping()
/*      */     {
/*      */       public Type getIdentifierType(String persistentClass)
/*      */         throws MappingException
/*      */       {
/* 2141 */         PersistentClass pc = (PersistentClass)Configuration.this.classes.get(persistentClass);
/* 2142 */         if (pc == null) {
/* 2143 */           throw new MappingException("persistent class not known: " + persistentClass);
/*      */         }
/* 2145 */         return pc.getIdentifier().getType();
/*      */       }
/*      */ 
/*      */       public String getIdentifierPropertyName(String persistentClass) throws MappingException {
/* 2149 */         PersistentClass pc = (PersistentClass)Configuration.this.classes.get(persistentClass);
/* 2150 */         if (pc == null) {
/* 2151 */           throw new MappingException("persistent class not known: " + persistentClass);
/*      */         }
/* 2153 */         if (!pc.hasIdentifierProperty()) {
/* 2154 */           return null;
/*      */         }
/* 2156 */         return pc.getIdentifierProperty().getName();
/*      */       }
/*      */ 
/*      */       public Type getReferencedPropertyType(String persistentClass, String propertyName) throws MappingException {
/* 2160 */         PersistentClass pc = (PersistentClass)Configuration.this.classes.get(persistentClass);
/* 2161 */         if (pc == null) {
/* 2162 */           throw new MappingException("persistent class not known: " + persistentClass);
/*      */         }
/* 2164 */         Property prop = pc.getReferencedProperty(propertyName);
/* 2165 */         if (prop == null) {
/* 2166 */           throw new MappingException(
/* 2167 */             "property not known: " + 
/* 2168 */             persistentClass + '.' + propertyName);
/*      */         }
/*      */ 
/* 2171 */         return prop.getType();
/*      */       } } ;
/*      */   }
/*      */ 
/*      */   private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
/* 2177 */     ois.defaultReadObject();
/* 2178 */     this.mapping = buildMapping();
/* 2179 */     this.xmlHelper = new XMLHelper();
/*      */   }
/*      */ 
/*      */   public Map getFilterDefinitions() {
/* 2183 */     return this.filterDefinitions;
/*      */   }
/*      */ 
/*      */   public void addFilterDefinition(FilterDefinition definition) {
/* 2187 */     this.filterDefinitions.put(definition.getFilterName(), definition);
/*      */   }
/*      */ 
/*      */   public void addAuxiliaryDatabaseObject(AuxiliaryDatabaseObject object) {
/* 2191 */     this.auxiliaryDatabaseObjects.add(object);
/*      */   }
/*      */ 
/*      */   public Map getSqlFunctions() {
/* 2195 */     return this.sqlFunctions;
/*      */   }
/*      */ 
/*      */   public void addSqlFunction(String functionName, SQLFunction function) {
/* 2199 */     this.sqlFunctions.put(functionName, function);
/*      */   }
/*      */ 
/*      */   public SessionFactoryObserver getSessionFactoryObserver() {
/* 2203 */     return this.sessionFactoryObserver;
/*      */   }
/*      */ 
/*      */   public void setSessionFactoryObserver(SessionFactoryObserver sessionFactoryObserver) {
/* 2207 */     this.sessionFactoryObserver = sessionFactoryObserver;
/*      */   }
/*      */ 
/*      */   public Mapping getMapping()
/*      */   {
/* 2213 */     return this.mapping;
/*      */   }
/*      */ 
/*      */   public Map getClassMap() {
/* 2217 */     return this.classes;
/*      */   }
/*      */ 
/*      */   public Map getCollectionMap() {
/* 2221 */     return this.collections;
/*      */   }
/*      */ 
/*      */   public void doComplie() {
/* 2225 */     secondPassCompile();
/*      */   }
/*      */ }

/* Location:           E:\Workspace\Template Projects\estbpm-orcl\estbpm\WEB-INF\classes\
 * Qualified Name:     org.hibernate.cfg.Configuration
 * JD-Core Version:    0.6.0
 */
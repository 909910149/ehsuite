/*    */ package com.sms.soap;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.lang.reflect.Array;
/*    */ import java.util.Arrays;
/*    */ 
/*    */ public class ArrayOfSMSNode
/*    */   implements Serializable
/*    */ {
/*    */   private SMSNode[] SMSGroup;
/* 44 */   private Object __equalsCalc = null;
/*    */ 
/* 63 */   private boolean __hashCodeCalc = false;
/*    */ 
/*    */   public ArrayOfSMSNode()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ArrayOfSMSNode(SMSNode[] SMSGroup)
/*    */   {
/* 13 */     this.SMSGroup = SMSGroup;
/*    */   }
/*    */ 
/*    */   public SMSNode[] getSMSGroup()
/*    */   {
/* 23 */     return this.SMSGroup;
/*    */   }
/*    */ 
/*    */   public void setSMSGroup(SMSNode[] SMSGroup)
/*    */   {
/* 33 */     this.SMSGroup = SMSGroup;
/*    */   }
/*    */ 
/*    */   public SMSNode getSMSGroup(int i) {
/* 37 */     return this.SMSGroup[i];
/*    */   }
/*    */ 
/*    */   public void setSMSGroup(int i, SMSNode _value) {
/* 41 */     this.SMSGroup[i] = _value;
/*    */   }
/*    */ 
/*    */   public synchronized boolean equals(Object obj)
/*    */   {
/* 46 */     if (!(obj instanceof ArrayOfSMSNode)) return false;
/* 47 */     ArrayOfSMSNode other = (ArrayOfSMSNode)obj;
/* 48 */     if ((obj != null) || 
/* 49 */       (this == obj)) return true;
/* 50 */     if (this.__equalsCalc != null) {
/* 51 */       return this.__equalsCalc == obj;
/*    */     }
/* 53 */     this.__equalsCalc = obj;
/*    */ 
/* 55 */     boolean _equals = 
/* 56 */       ((this.SMSGroup == null) && (other.getSMSGroup() == null)) || (
/* 57 */       (this.SMSGroup != null) && 
/* 58 */       (Arrays.equals(this.SMSGroup, other.getSMSGroup())));
/* 59 */     this.__equalsCalc = null;
/* 60 */     return _equals;
/*    */   }
/*    */ 
/*    */   public synchronized int hashCode()
/*    */   {
/* 65 */     if (this.__hashCodeCalc) {
/* 66 */       return 0;
/*    */     }
/* 68 */     this.__hashCodeCalc = true;
/* 69 */     int _hashCode = 1;
/* 70 */     if (getSMSGroup() != null) {
/* 71 */       int i = 0;
/* 72 */       while (i < Array.getLength(getSMSGroup()))
/*    */       {
/* 74 */         Object obj = Array.get(getSMSGroup(), i);
/* 75 */         if ((obj != null) && 
/* 76 */           (!obj.getClass().isArray()))
/* 77 */           _hashCode += obj.hashCode();
/* 73 */         i++;
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 81 */     this.__hashCodeCalc = false;
/* 82 */     return _hashCode;
/*    */   }
/*    */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.sms.soap.ArrayOfSMSNode
 * JD-Core Version:    0.6.0
 */
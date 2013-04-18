 package com.htsoft.core.jbpm.jpdl;
 
 public class AnchorArea
 {
   private Integer startX;
   private Integer startY;
   private Integer endX;
   private Integer endY;
   private String activityName;
   private String nodeType;
 
   public AnchorArea()
   {
   }
 
   public AnchorArea(Integer startX, Integer startY, Integer endX, Integer endY, String activityName, String nodeType)
   {
     this.startX = startX;
     this.startY = startY;
     this.endX = endX;
     this.endY = endY;
     this.activityName = activityName;
     this.nodeType = nodeType;
   }
 
   public Integer getStartX()
   {
     return this.startX;
   }
 
   public void setStartX(Integer startX) {
     this.startX = startX;
   }
 
   public Integer getStartY() {
     return this.startY;
   }
 
   public void setStartY(Integer startY) {
     this.startY = startY;
   }
 
   public Integer getEndX() {
     return this.endX;
   }
 
   public void setEndX(Integer endX) {
     this.endX = endX;
   }
 
   public Integer getEndY() {
     return this.endY;
   }
 
   public void setEndY(Integer endY) {
     this.endY = endY;
   }
 
   public String getActivityName() {
     return this.activityName;
   }
 
   public void setActivityName(String activityName) {
     this.activityName = activityName;
   }
 
   public String getNodeType() {
     return this.nodeType;
   }
 
   public void setNodeType(String nodeType) {
     this.nodeType = nodeType;
   }
 }


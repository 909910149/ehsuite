/*      */ package com.htsoft.oa.service.bpm.ILog.helper;
/*      */ 
/*      */ public class AddTransition
/*      */ {
/*      */   private static final int currentWidth = 20;
/*      */   private static final int STARTLABELHeight = 12;
/*   23 */   private String str = "";
/*      */ 
/*   25 */   private String A = "";
/*   26 */   private String B = "";
/*   27 */   private String C = "";
/*   28 */   private String D = "";
/*      */ 
/*      */   public String getPointString(String start, String end, int[] starts, int[] ends, String nodeType, String endNodeType)
/*      */   {
/*   47 */     if ((start != null) && (start.equals("x=0")))
/*      */     {
/*   49 */       if ((end != null) && (end.equals("x=0")))
/*   50 */         getPoint_x_0_x_0(starts, ends);
/*   51 */       else if ((end != null) && (end.equals("x=1")))
/*   52 */         getPoint_x_0_x_1(starts, ends, nodeType, endNodeType);
/*   53 */       else if ((end != null) && (end.equals("y=0")))
/*   54 */         getPoint_x_0_y_0(starts, ends);
/*   55 */       else if ((end != null) && (end.equals("y=1")))
/*   56 */         getPoint_x_0_y_1(starts, ends);
/*   57 */     } else if ((start != null) && (start.equals("x=1")))
/*      */     {
/*   59 */       if ((end != null) && (end.equals("x=0")))
/*   60 */         getPoint_x_1_x_0(starts, ends, nodeType, endNodeType);
/*   61 */       else if ((end != null) && (end.equals("x=1")))
/*   62 */         getPoint_x_1_x_1(starts, ends);
/*   63 */       else if ((end != null) && (end.equals("y=0")))
/*   64 */         getPoint_x_1_y_0(starts, ends);
/*   65 */       else if ((end != null) && (end.equals("y=1")))
/*   66 */         getPoint_x_1_y_1(starts, ends);
/*   67 */     } else if ((start != null) && (start.equals("y=0")))
/*      */     {
/*   69 */       if ((end != null) && (end.equals("x=0")))
/*   70 */         getPoint_y_0_x_0(starts, ends, nodeType);
/*   71 */       else if ((end != null) && (end.equals("x=1")))
/*   72 */         getPoint_y_0_x_1(starts, ends, nodeType);
/*   73 */       else if ((end != null) && (end.equals("y=0")))
/*   74 */         getPoint_y_0_y_0(starts, ends, nodeType);
/*   75 */       else if ((end != null) && (end.equals("y=1")))
/*   76 */         getPoint_y_0_y_1(starts, ends, nodeType, endNodeType);
/*   77 */     } else if ((start != null) && (start.equals("y=1")))
/*      */     {
/*   79 */       if ((end != null) && (end.equals("x=0")))
/*   80 */         getPoint_y_1_x_0(starts, ends, nodeType);
/*   81 */       else if ((end != null) && (end.equals("x=1")))
/*   82 */         getPoint_y_1_x_1(starts, ends, nodeType);
/*   83 */       else if ((end != null) && (end.equals("y=0")))
/*   84 */         getPoint_y_1_y_0(starts, ends, nodeType, endNodeType);
/*   85 */       else if ((end != null) && (end.equals("y=1")))
/*   86 */         getPoint_y_1_y_1(starts, ends, nodeType);
/*      */     }
/*   88 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String getPoint_x_0_x_0(int[] starts, int[] ends)
/*      */   {
/*  108 */     boolean bo = (starts[1] <= ends[1] - starts[3] / 2) || ((starts[0] <= ends[0] + ends[2]) && 
/*  109 */       (starts[1] >= ends[1] - starts[3] / 2) && (starts[1] <= ends[1] + ends[3] - starts[3] / 2)) || 
/*  110 */       (starts[1] >= ends[1] + ends[3] - starts[3] / 2);
/*  111 */     if ((starts[0] >= ends[0]) && (bo)) {
/*  112 */       this.A = (ends[0] - 20 + "," + (starts[1] + starts[3] / 2));
/*  113 */       this.B = (ends[0] - 20 + "," + (ends[1] + ends[3] / 2));
/*  114 */       this.str = (this.A + ";" + this.B);
/*  115 */     } else if ((starts[0] >= ends[0] + ends[2]) && (starts[1] >= ends[1] - starts[3] / 2) && (starts[1] <= ends[1] + ends[3] / 2 - starts[3] / 2))
/*      */     {
/*  122 */       this.A = ((starts[0] + ends[0] + ends[2]) / 2 + "," + (starts[1] + starts[3] / 2));
/*  123 */       this.B = ((starts[0] + ends[0] + ends[2]) / 2 + "," + (ends[1] - 20));
/*  124 */       this.C = (ends[0] - 20 + "," + (ends[1] - 20));
/*  125 */       this.D = (ends[0] - 20 + "," + (ends[1] + ends[3] / 2));
/*  126 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  127 */     } else if ((starts[0] >= ends[0] + ends[2]) && (starts[1] >= ends[1] + ends[3] / 2 - starts[3] / 2) && (starts[1] <= ends[1] + ends[3] - starts[3] / 2))
/*      */     {
/*  134 */       this.A = ((starts[0] + ends[0] + ends[2]) / 2 + "," + (starts[1] + starts[3] / 2));
/*  135 */       this.B = ((starts[0] + ends[0] + ends[2]) / 2 + "," + (ends[1] + ends[3] + 20));
/*  136 */       this.C = (ends[0] - 20 + "," + (ends[1] + ends[3] + 20));
/*  137 */       this.D = (ends[0] - 20 + "," + (ends[1] + ends[3] / 2));
/*  138 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  139 */     } else if ((starts[0] <= ends[0]) && (starts[1] <= ends[1] + ends[3] / 2 - starts[3] / 2) && (starts[1] >= ends[1] + ends[3] / 2 - starts[3] / 2))
/*      */     {
/*  146 */       this.A = (starts[0] - starts[2] / 2 + "," + (starts[1] + starts[3] / 2));
/*  147 */       this.B = (starts[0] - starts[2] / 2 + "," + (starts[1] - 20));
/*  148 */       this.C = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (starts[1] - 20));
/*  149 */       this.D = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (ends[1] + ends[3] / 2));
/*  150 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  151 */     } else if ((starts[0] <= ends[0]) && (starts[1] >= ends[1] - starts[3] / 2) && (starts[1] <= ends[1] + ends[3] / 2 - starts[3] / 2))
/*      */     {
/*  158 */       this.A = (starts[0] - starts[2] / 2 + "," + (starts[1] + starts[3] / 2));
/*  159 */       this.B = (starts[0] - starts[2] / 2 + "," + (starts[1] + starts[3] + 20));
/*  160 */       this.C = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (starts[1] + starts[3] + 20));
/*  161 */       this.D = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (ends[1] + ends[3] / 2));
/*  162 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*      */     }
/*      */     else
/*      */     {
/*  166 */       this.A = (starts[0] - 20 + "," + (starts[1] + starts[3] / 2));
/*  167 */       this.B = (starts[0] - 20 + "," + (ends[1] + ends[3] / 2));
/*  168 */       this.str = (this.A + ";" + this.B);
/*      */     }
/*  170 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String getPoint_x_0_x_1(int[] starts, int[] ends, String nodeType, String endNodeType)
/*      */   {
/*  184 */     boolean isFourth = starts[0] < ends[0] + ends[2];
/*  185 */     if (isFourth)
/*      */     {
/*  188 */       if ((starts[0] <= ends[0] + ends[2]) && (starts[1] <= ends[1] - starts[3]))
/*      */       {
/*  195 */         this.A = (starts[0] - 20 + "," + (starts[1] + starts[3] / 2));
/*  196 */         this.B = (starts[0] - 20 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/*  197 */         this.C = (ends[0] + ends[2] + 20 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/*  198 */         this.D = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] / 2));
/*  199 */       } else if ((starts[0] <= ends[0]) && (starts[1] <= ends[1] + ends[3] / 2 - starts[3] / 2) && (starts[1] >= ends[1] - starts[3]))
/*      */       {
/*  206 */         this.A = (starts[0] - 20 - starts[3] / 2 + "," + (starts[1] + starts[3] / 2));
/*  207 */         this.B = (starts[0] - 20 - starts[3] / 2 + "," + (ends[1] + ends[3] + 20));
/*  208 */         this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] + 20));
/*  209 */         this.D = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] / 2));
/*  210 */       } else if ((starts[0] <= ends[0]) && (starts[1] >= ends[1] + ends[3] / 2 - starts[3] / 2) && (starts[1] <= ends[1] + ends[3]))
/*      */       {
/*  217 */         this.A = (starts[0] - 20 + "," + (starts[1] + starts[3] / 2));
/*  218 */         this.B = (starts[0] - 20 + "," + (ends[1] - 20));
/*  219 */         this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] - 20));
/*  220 */         this.D = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] / 2));
/*  221 */       } else if ((starts[0] >= ends[0]) && (starts[0] <= ends[0] + ends[2]) && (starts[1] >= ends[1] + ends[3] / 2 - starts[3] / 2) && (starts[1] <= ends[1] + ends[3]))
/*      */       {
/*  224 */         this.A = (ends[0] - 20 + "," + (starts[1] + starts[3] / 2));
/*  225 */         this.B = (ends[0] - 20 + "," + (ends[1] - 20));
/*  226 */         this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] - 20));
/*  227 */         this.D = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] / 2));
/*      */       }
/*  229 */       else if ((starts[0] >= ends[0]) && (starts[0] <= ends[0] + ends[2]) && (starts[1] <= ends[1] + ends[3] / 2 - starts[3] / 2) && (starts[1] >= ends[1] - starts[3]))
/*      */       {
/*  232 */         this.A = (ends[0] - 20 + "," + (starts[1] + starts[3] / 2));
/*  233 */         this.B = (ends[0] - 20 + "," + (ends[1] + ends[3] + 20));
/*  234 */         this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] + 20));
/*  235 */         this.D = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] / 2));
/*      */       }
/*      */       else
/*      */       {
/*  242 */         this.A = (starts[0] - 20 + "," + (starts[1] + starts[3] / 2));
/*  243 */         this.B = (starts[0] - 20 + "," + (starts[1] + ends[1] + ends[3]) / 2);
/*  244 */         this.C = (ends[0] + ends[2] + 20 + "," + (starts[1] + ends[1] + ends[3]) / 2);
/*  245 */         this.D = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] / 2));
/*      */       }
/*  247 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*      */     }
/*      */     else
/*      */     {
/*  251 */       int value = Math.abs(starts[1] + starts[3] - ends[1] - ends[3] / 2);
/*  252 */       int subLength = getNodesSubLength(nodeType, endNodeType);
/*  253 */       if ((value >= 0) && (value <= subLength)) {
/*  254 */         this.str = "";
/*      */       }
/*      */       else {
/*  257 */         this.A = ((starts[0] + ends[0] + ends[2]) / 2 + "," + (starts[1] + starts[3] / 2));
/*  258 */         this.B = ((starts[0] + ends[0] + ends[2]) / 2 + "," + (ends[1] + ends[3] / 2));
/*  259 */         this.str = (this.A + ";" + this.B);
/*      */       }
/*      */     }
/*      */ 
/*  263 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String getPoint_x_0_y_0(int[] starts, int[] ends)
/*      */   {
/*  274 */     if ((starts[0] >= ends[0]) && (starts[1] <= ends[1] - starts[3] / 2))
/*      */     {
/*  279 */       this.A = (ends[0] + ends[2] / 2 + "," + (starts[1] + starts[3] / 2));
/*  280 */       this.str = this.A;
/*  281 */     } else if ((starts[0] <= ends[0] + ends[2] / 2) && (starts[1] <= ends[1] - starts[3] / 2))
/*      */     {
/*  287 */       this.A = (starts[0] - 20 + "," + (starts[1] + starts[3] / 2));
/*  288 */       this.B = (starts[0] - 20 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/*  289 */       this.C = (ends[0] + ends[2] / 2 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/*  290 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*  291 */     } else if ((starts[0] <= ends[0]) && (starts[1] >= ends[1] - starts[3]))
/*      */     {
/*  297 */       this.A = (starts[0] - 20 + "," + (starts[1] + starts[3] / 2));
/*  298 */       this.B = (starts[0] - 20 + "," + (ends[1] - 20));
/*  299 */       this.C = (ends[0] + ends[2] / 2 + "," + (ends[1] - 20));
/*  300 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*  301 */     } else if ((starts[0] >= ends[0]) && (starts[1] >= ends[1] - starts[3]) && (starts[0] <= ends[1] + ends[2]))
/*      */     {
/*  307 */       this.A = (ends[0] - 20 + "," + (starts[1] + starts[3] / 2));
/*  308 */       this.B = (ends[0] - 20 + "," + (ends[1] - 20));
/*  309 */       this.C = (ends[0] + ends[2] / 2 + "," + (ends[1] - 20));
/*  310 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*      */     }
/*      */     else
/*      */     {
/*  317 */       this.A = ((ends[0] + ends[2] + starts[0]) / 2 + "," + (starts[1] + starts[3] / 2));
/*  318 */       this.B = ((ends[0] + ends[2] + starts[0]) / 2 + "," + (ends[1] - 20));
/*  319 */       this.C = (ends[0] + ends[2] / 2 + "," + (ends[1] - 20));
/*  320 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*      */     }
/*  322 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String getPoint_x_0_y_1(int[] starts, int[] ends)
/*      */   {
/*  334 */     if ((starts[0] >= ends[0] + ends[2] / 2) && (starts[1] >= ends[1] + ends[3] - starts[3] / 2))
/*      */     {
/*  338 */       this.A = (ends[0] + ends[2] / 2 + "," + (starts[1] + starts[3] / 2));
/*  339 */       this.str = this.A;
/*  340 */     } else if ((starts[0] <= ends[0] + ends[2] / 2) && (starts[1] >= ends[1] + ends[3]))
/*      */     {
/*  346 */       this.A = (starts[0] - 20 + "," + (starts[1] + starts[3] / 2));
/*  347 */       this.B = (starts[0] - 20 + "," + (ends[1] + ends[3] + starts[1]) / 2);
/*  348 */       this.C = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + starts[1]) / 2);
/*  349 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*  350 */     } else if ((starts[0] <= ends[0]) && (starts[1] <= ends[1] + ends[3]))
/*      */     {
/*  356 */       this.A = (starts[0] - starts[2] / 2 - 20 + "," + (starts[1] + starts[3] / 2));
/*  357 */       this.B = (starts[0] - starts[2] / 2 - 20 + "," + (ends[1] + ends[3] + 20));
/*  358 */       this.C = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + 20));
/*  359 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*  360 */     } else if ((starts[0] >= ends[0]) && (starts[0] <= ends[0] + ends[2]) && (starts[1] <= ends[1] + ends[3]))
/*      */     {
/*  366 */       this.A = (ends[0] - 20 + "," + (starts[1] + starts[3] / 2));
/*  367 */       this.B = (ends[0] - 20 + "," + (ends[1] + ends[3] + 20));
/*  368 */       this.C = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + 20));
/*  369 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*      */     }
/*      */     else
/*      */     {
/*  376 */       this.A = ((starts[0] + ends[0] + ends[2]) / 2 + "," + (starts[1] + starts[3] / 2));
/*  377 */       this.B = ((starts[0] + ends[0] + ends[2]) / 2 + "," + (ends[1] + ends[3] + 20));
/*  378 */       this.C = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + 20));
/*  379 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*      */     }
/*  381 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String getPoint_x_1_x_0(int[] starts, int[] ends, String startNode, String endNode)
/*      */   {
/*  399 */     if (starts[0] <= ends[0] - starts[2])
/*      */     {
/*  402 */       int value = Math.abs(starts[1] + starts[3] / 2 - ends[1] - ends[3] / 2);
/*  403 */       int subLength = getNodesSubLength(startNode, endNode);
/*  404 */       if ((value >= 0) && (value <= subLength)) {
/*  405 */         this.str = "";
/*      */       }
/*      */       else
/*      */       {
/*  411 */         this.A = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (starts[1] + starts[3] / 2));
/*  412 */         this.B = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (ends[1] + ends[3] / 2));
/*  413 */         this.str = (this.A + ";" + this.B);
/*      */       }
/*  415 */     } else if ((starts[0] >= ends[0] - starts[2]) && (starts[1] <= ends[1] - starts[3]))
/*      */     {
/*  422 */       this.A = (starts[0] + starts[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  423 */       this.B = (starts[0] + starts[2] + 20 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/*  424 */       this.C = (ends[0] - 20 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/*  425 */       this.D = (ends[0] - 20 + "," + (ends[1] + ends[3] / 2));
/*  426 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  427 */     } else if ((starts[0] >= ends[0] + ends[2] - starts[2]) && (starts[1] >= ends[1] - starts[3]) && (starts[1] <= ends[1] + ends[3] / 2 - starts[3] / 2))
/*      */     {
/*  434 */       this.A = (starts[0] + starts[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  435 */       this.B = (starts[0] + starts[2] + 20 + "," + (ends[1] + ends[3] + 20));
/*  436 */       this.C = (ends[0] - 20 + "," + (ends[1] + ends[3] + 20));
/*  437 */       this.D = (ends[0] - 20 + "," + (ends[1] + ends[3] / 2));
/*  438 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  439 */     } else if ((starts[0] >= ends[0] - starts[2]) && (starts[0] <= ends[0] + ends[2] - starts[2]) && (starts[1] >= ends[1] - starts[3]) && (starts[1] <= ends[1] + ends[3] / 2 - starts[3] / 2))
/*      */     {
/*  446 */       this.A = (ends[0] + ends[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  447 */       this.B = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] + 20));
/*  448 */       this.C = (ends[0] - 20 + "," + (ends[1] + ends[3] + 20));
/*  449 */       this.D = (ends[0] - 20 + "," + (ends[1] + ends[3] / 2));
/*  450 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  451 */     } else if ((starts[0] >= ends[0] + ends[2] - starts[2]) && (starts[1] >= ends[1] + ends[3] / 2 - starts[3] / 2) && (starts[1] <= ends[1] + ends[3]))
/*      */     {
/*  458 */       this.A = (starts[0] + starts[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  459 */       this.B = (starts[0] + starts[2] + 20 + "," + (ends[1] - 20));
/*  460 */       this.C = (ends[0] - 20 + "," + (ends[1] - 20));
/*  461 */       this.D = (ends[0] - 20 + "," + (ends[1] + ends[3] / 2));
/*  462 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  463 */     } else if ((starts[0] >= ends[0] - starts[2]) && (starts[0] <= ends[0] + ends[2] - starts[2]) && (starts[1] >= ends[1] + ends[3] / 2 - starts[3] / 2) && (starts[1] <= ends[1] + ends[3]))
/*      */     {
/*  470 */       this.A = (ends[0] + ends[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  471 */       this.B = (ends[0] + ends[2] + 20 + "," + (ends[1] - 20));
/*  472 */       this.C = (ends[0] - 20 + "," + (ends[1] - 20));
/*  473 */       this.D = (ends[0] - 20 + "," + (ends[1] + ends[3] / 2));
/*  474 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*      */     }
/*      */     else
/*      */     {
/*  482 */       this.A = (starts[0] + starts[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  483 */       this.B = (starts[0] + starts[2] + 20 + "," + (ends[1] + ends[3] + starts[1]) / 2);
/*  484 */       this.C = (ends[0] - 20 + "," + (ends[1] + ends[3] + starts[1]) / 2);
/*  485 */       this.D = (ends[0] - 20 + "," + (ends[1] + ends[3] / 2));
/*  486 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*      */     }
/*  488 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String getPoint_x_1_x_1(int[] starts, int[] ends)
/*      */   {
/*  500 */     boolean bo_t = ((starts[0] <= ends[0] + ends[2] - starts[2]) && (starts[1] <= ends[1] - starts[3] / 2)) || ((starts[0] >= ends[0] - starts[2]) && 
/*  501 */       (starts[0] <= ends[0] + ends[2]) && (starts[1] >= ends[1] - ends[3] / 2) && (starts[1] < ends[1] + ends[3] / 2 - starts[3] / 2));
/*      */ 
/*  503 */     boolean bo_b = ((starts[0] <= ends[0] + ends[2] - starts[2]) && (starts[1] >= ends[1] + ends[3] - starts[3] / 2)) || (
/*  504 */       (starts[0] >= ends[0] - starts[2]) && (starts[0] <= ends[0] + ends[2]) && (starts[1] >= ends[1] + ends[3] / 2 - starts[3] / 2) && 
/*  505 */       (starts[1] <= ends[1] + ends[3] - starts[3] / 2));
/*  506 */     if (bo_t)
/*      */     {
/*  512 */       this.A = (ends[0] + ends[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  513 */       this.B = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] / 2));
/*  514 */       this.str = (this.A + ";" + this.B);
/*  515 */     } else if ((starts[0] >= ends[0] + ends[2]) && (starts[1] >= ends[1] + ends[3] / 2 - starts[3]) && (starts[1] <= ends[1] + ends[3] / 2 - starts[3] / 2))
/*      */     {
/*  522 */       this.A = (starts[0] + starts[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  523 */       this.B = (starts[0] + starts[2] + 20 + "," + (starts[1] + starts[3] + 20));
/*  524 */       this.C = ((ends[0] + ends[2] + starts[0]) / 2 + "," + (starts[1] + starts[3] + 20));
/*  525 */       this.D = ((ends[0] + ends[2] + starts[0]) / 2 + "," + (ends[1] + ends[3] / 2));
/*  526 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  527 */     } else if ((starts[0] >= ends[0] + ends[2]) && (starts[1] >= ends[1] + ends[3] / 2 - starts[3] / 2) && (starts[1] <= ends[1] + ends[3] / 2))
/*      */     {
/*  534 */       this.A = (starts[0] + starts[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  535 */       this.B = (starts[0] + starts[2] + 20 + "," + (starts[1] - 20));
/*  536 */       this.C = ((ends[0] + ends[2] + starts[0]) / 2 + "," + (starts[1] - 20));
/*  537 */       this.D = ((ends[0] + ends[2] + starts[0]) / 2 + "," + (ends[1] + ends[3] / 2));
/*  538 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  539 */     } else if (bo_b)
/*      */     {
/*  545 */       this.A = (ends[0] + ends[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  546 */       this.B = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] / 2));
/*  547 */       this.str = (this.A + ";" + this.B);
/*  548 */     } else if ((starts[0] <= ends[0] - starts[2]) && (starts[1] >= ends[1] - starts[3] / 2) && (starts[1] <= ends[1] + ends[3] / 2 - starts[3] / 2))
/*      */     {
/*  555 */       this.A = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (starts[1] + starts[3] / 2));
/*  556 */       this.B = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (ends[1] - 20));
/*  557 */       this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] - 20));
/*  558 */       this.D = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] / 2));
/*  559 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  560 */     } else if ((starts[0] <= ends[0] - starts[2]) && (starts[1] >= ends[1] + ends[3] / 2 - starts[3] / 2) && (starts[1] <= ends[1] + ends[1] - starts[3] / 2))
/*      */     {
/*  567 */       this.A = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (starts[1] + starts[3] / 2));
/*  568 */       this.B = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (ends[1] + ends[3] + 20));
/*  569 */       this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] + 20));
/*  570 */       this.D = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] / 2));
/*  571 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*      */     }
/*      */     else
/*      */     {
/*  578 */       this.A = (starts[0] + starts[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  579 */       this.B = (starts[0] + starts[2] + 20 + "," + (ends[1] + ends[3] / 2));
/*  580 */       this.str = (this.A + ";" + this.B);
/*      */     }
/*  582 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String getPoint_x_1_y_0(int[] starts, int[] ends)
/*      */   {
/*  593 */     if ((starts[0] <= ends[0] + ends[2] / 2 - starts[2]) && (starts[1] <= ends[1] - starts[3] / 2))
/*      */     {
/*  597 */       this.A = (ends[0] + ends[2] / 2 + "," + (starts[1] + starts[3] / 2));
/*  598 */       this.str = this.A;
/*  599 */     } else if ((starts[0] <= ends[0] - starts[2]) && (starts[1] >= ends[1] - starts[3] / 2))
/*      */     {
/*  605 */       this.A = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (starts[1] + starts[3] / 2));
/*  606 */       this.B = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (ends[1] - 20));
/*  607 */       this.C = (ends[0] + ends[2] / 2 + "," + (ends[1] - 20));
/*  608 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*  609 */     } else if ((starts[0] >= ends[0] + ends[2] / 2 - starts[2]) && (starts[1] <= ends[1] - starts[3]))
/*      */     {
/*  615 */       this.A = (starts[0] + starts[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  616 */       this.B = (starts[0] + starts[2] + 20 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/*  617 */       this.C = (ends[0] + ends[2] / 2 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/*  618 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*  619 */     } else if ((starts[0] >= ends[0] + ends[2] - starts[2]) && (starts[1] >= ends[1] - starts[3]))
/*      */     {
/*  625 */       this.A = (starts[0] + starts[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  626 */       this.B = (starts[0] + starts[2] + 20 + "," + (ends[1] - 20));
/*  627 */       this.C = (ends[0] + ends[2] / 2 + "," + (ends[1] - 20));
/*  628 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*      */     }
/*      */     else
/*      */     {
/*  635 */       this.A = (ends[0] + ends[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  636 */       this.B = (ends[0] + ends[2] + 20 + "," + (ends[1] - 20));
/*  637 */       this.C = (ends[0] + ends[2] / 2 + "," + (ends[1] - 20));
/*  638 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*      */     }
/*  640 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String getPoint_x_1_y_1(int[] starts, int[] ends)
/*      */   {
/*  652 */     if ((starts[0] <= ends[0] - starts[2]) && (starts[1] <= ends[1] + ends[3] - starts[3] / 2))
/*      */     {
/*  658 */       this.A = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (starts[1] + starts[3] / 2));
/*  659 */       this.B = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (ends[1] + ends[3] + 20));
/*  660 */       this.C = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + 20));
/*  661 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*  662 */     } else if ((starts[0] <= ends[0] + ends[2] / 2 - starts[2]) && (starts[1] >= ends[1] + ends[3] - starts[3] / 2))
/*      */     {
/*  666 */       this.A = (ends[0] + ends[2] / 2 + "," + (starts[1] + starts[3] / 2));
/*  667 */       this.str = this.A;
/*  668 */     } else if ((starts[0] >= ends[0] + ends[2] - starts[2]) && (starts[1] <= ends[1] + ends[3]))
/*      */     {
/*  674 */       this.A = (starts[0] + starts[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  675 */       this.B = (starts[0] + starts[2] + 20 + "," + (ends[1] + ends[3] + 20));
/*  676 */       this.C = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + 20));
/*  677 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*  678 */     } else if ((starts[0] >= ends[0] + ends[2] / 2 - starts[2]) && (starts[1] >= ends[1] + ends[3]))
/*      */     {
/*  684 */       this.A = (starts[0] + starts[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  685 */       this.B = (starts[0] + starts[2] + 20 + "," + (ends[1] + ends[3] + starts[1]) / 2);
/*  686 */       this.C = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + starts[1]) / 2);
/*  687 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*      */     }
/*      */     else
/*      */     {
/*  694 */       this.A = (ends[0] + ends[2] + 20 + "," + (starts[1] + starts[3] / 2));
/*  695 */       this.B = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] + 20));
/*  696 */       this.C = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + 20));
/*  697 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*      */     }
/*  699 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String getPoint_y_0_x_0(int[] starts, int[] ends, String nodeType)
/*      */   {
/*  716 */     if ((starts[0] <= ends[0] - starts[2]) && (starts[1] <= ends[1] + starts[3] / 2))
/*      */     {
/*  722 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] - 20);
/*  723 */       this.B = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (starts[1] - 20));
/*  724 */       this.C = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (ends[1] + ends[3] / 2));
/*  725 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*  726 */     } else if ((starts[0] <= ends[0] - starts[2] / 2) && (starts[1] >= ends[1] + ends[3] / 2))
/*      */     {
/*  730 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, ends[1] + ends[3] / 2);
/*  731 */       this.str = this.A;
/*  732 */     } else if ((starts[0] >= ends[0] - starts[2]) && (starts[1] <= ends[1]))
/*      */     {
/*  738 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] - 20);
/*  739 */       this.B = (ends[0] - 20 + "," + (starts[1] - 20));
/*  740 */       this.C = (ends[0] - 20 + "," + (ends[1] + ends[3] / 2));
/*  741 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*  742 */     } else if ((starts[0] >= ends[0] - starts[2] / 2) && (starts[1] >= ends[1] + ends[3]))
/*      */     {
/*  748 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, (ends[1] + ends[3] + starts[1]) / 2);
/*  749 */       this.B = (ends[0] - 20 + "," + (ends[1] + ends[3] + starts[1]) / 2);
/*  750 */       this.C = (ends[0] - 20 + "," + (ends[1] + ends[3] / 2));
/*  751 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*      */     }
/*      */     else
/*      */     {
/*  758 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, ends[1] - 20);
/*  759 */       this.B = (ends[0] - 20 + "," + (ends[1] - 20));
/*  760 */       this.C = (ends[0] - 20 + "," + (ends[1] + ends[3] / 2));
/*  761 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*      */     }
/*  763 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String getPoint_y_0_x_1(int[] starts, int[] ends, String nodeType)
/*      */   {
/*  776 */     if ((starts[0] <= ends[0] + ends[2]) && (starts[1] <= ends[1]))
/*      */     {
/*  782 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] - 20);
/*  783 */       this.B = (ends[0] + ends[2] + 20 + "," + (starts[1] - 20));
/*  784 */       this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] / 2));
/*  785 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*  786 */     } else if ((starts[0] <= ends[0] + ends[2] - starts[2] / 2) && (starts[1] >= ends[1] + ends[3]))
/*      */     {
/*  792 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, (ends[1] + ends[3] + starts[1]) / 2);
/*  793 */       this.B = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] + starts[1]) / 2);
/*  794 */       this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] / 2));
/*  795 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*  796 */     } else if ((starts[0] >= ends[0] + ends[2]) && (starts[1] <= ends[1] + ends[3] / 2))
/*      */     {
/*  802 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] - 20);
/*  803 */       this.B = ((ends[0] + ends[2] + starts[0]) / 2 + "," + (starts[1] - 20));
/*  804 */       this.C = ((ends[0] + ends[2] + starts[0]) / 2 + "," + (ends[1] + ends[3] / 2));
/*  805 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*  806 */     } else if ((starts[0] >= ends[0] + ends[2] - starts[2] / 2) && (starts[1] >= ends[1] + ends[3] / 2))
/*      */     {
/*  810 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, ends[1] + ends[3] / 2);
/*  811 */       this.str = this.A;
/*      */     }
/*      */     else
/*      */     {
/*  818 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, ends[1] - 20);
/*  819 */       this.B = (ends[0] + ends[2] + 20 + "," + (ends[1] - 20));
/*  820 */       this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] / 2));
/*  821 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*      */     }
/*  823 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String getPoint_y_0_y_0(int[] starts, int[] ends, String nodeType)
/*      */   {
/*  834 */     if (((starts[0] <= ends[0] + ends[2] / 2 - starts[2]) || (starts[0] >= ends[0] + ends[2] / 2)) && (starts[1] <= ends[1]))
/*      */     {
/*  839 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] - 20);
/*  840 */       this.B = (ends[0] + ends[2] / 2 + "," + (starts[1] - 20));
/*  841 */       this.str = (this.A + ";" + this.B);
/*  842 */     } else if ((starts[0] >= ends[0] + ends[2] / 2 - starts[2]) && (starts[0] <= ends[0] + ends[2] / 2 - starts[2] / 2) && (starts[1] <= ends[1] - starts[3]))
/*      */     {
/*  849 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] - 20);
/*  850 */       this.B = (starts[0] + starts[2] + 20 + "," + (starts[1] - 20));
/*  851 */       this.C = (starts[0] + starts[2] + 20 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/*  852 */       this.D = (ends[0] + ends[2] / 2 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/*  853 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  854 */     } else if ((starts[0] >= ends[0] + ends[2] / 2 - starts[2] / 2) && (starts[0] <= ends[0] + ends[2] / 2) && (starts[1] <= ends[1] - starts[3]))
/*      */     {
/*  861 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] - 20);
/*  862 */       this.B = (starts[0] - 20 + "," + (starts[1] - 20));
/*  863 */       this.C = (starts[0] - 20 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/*  864 */       this.D = (ends[0] + ends[2] / 2 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/*  865 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  866 */     } else if ((starts[0] >= ends[0] - ends[2] / 2) && (starts[0] <= ends[0] + ends[2] / 2 - starts[2] / 2))
/*      */     {
/*  873 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, (ends[1] + ends[3] + starts[1]) / 2);
/*  874 */       this.B = (ends[0] - 20 + "," + (ends[1] + ends[3] + starts[1]) / 2);
/*  875 */       this.C = (ends[0] - 20 + "," + (ends[1] - 20));
/*  876 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] - 20));
/*  877 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  878 */     } else if ((starts[0] >= ends[0] + ends[2] / 2 - starts[2] / 2) && (starts[0] <= ends[0] + ends[2] - starts[2] / 2) && (starts[1] >= ends[1] + ends[3]))
/*      */     {
/*  885 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, (ends[1] + ends[3] + starts[1]) / 2);
/*  886 */       this.B = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] + starts[1]) / 2);
/*  887 */       this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] - 20));
/*  888 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] - 20));
/*  889 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*      */     }
/*      */     else
/*      */     {
/*  898 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, ends[1] - 20);
/*  899 */       this.B = (ends[0] + ends[2] / 2 + "," + (ends[1] - 20));
/*  900 */       this.str = (this.A + ";" + this.B);
/*      */     }
/*  902 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String getPoint_y_0_y_1(int[] starts, int[] ends, String nodeType, String endNodeType)
/*      */   {
/*  914 */     if ((starts[0] <= ends[0] - starts[2]) && (starts[1] <= ends[1] + ends[3]))
/*      */     {
/*  921 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] - 20);
/*  922 */       this.B = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (starts[1] - 20));
/*  923 */       this.C = ((starts[0] + starts[2] + ends[0]) / 2 + "," + (ends[1] + ends[3] + 20));
/*  924 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + 20));
/*  925 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  926 */     } else if ((starts[0] >= ends[0] - starts[2]) && (starts[0] <= ends[0] + ends[2] / 2 - starts[2] / 2) && (starts[1] >= ends[1]) && (starts[1] <= ends[1] + ends[3]))
/*      */     {
/*  933 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, ends[1] - 20);
/*  934 */       this.B = (ends[0] + ends[2] + 20 + "," + (ends[1] - 20));
/*  935 */       this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] + 20));
/*  936 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + 20));
/*  937 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  938 */     } else if ((starts[0] >= ends[0] + ends[2] / 2 - starts[2] / 2) && (starts[0] < ends[0] + ends[2]) && (starts[1] >= ends[1]) && (starts[1] <= ends[1] + ends[3]))
/*      */     {
/*  945 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, ends[1] - 20);
/*  946 */       this.B = (ends[0] - 20 + "," + (ends[1] - 20));
/*  947 */       this.C = (ends[0] - 20 + "," + (ends[1] + ends[3] + 20));
/*  948 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + 20));
/*  949 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  950 */     } else if ((starts[0] >= ends[0] - starts[2]) && (starts[0] <= ends[0] + ends[2] / 2 - starts[2] / 2) && (starts[1] <= ends[1] + ends[3]))
/*      */     {
/*  957 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] - 20);
/*  958 */       this.B = (ends[0] + ends[2] + 20 + "," + (starts[1] - 20));
/*  959 */       this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] + 20));
/*  960 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + 20));
/*  961 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  962 */     } else if ((starts[0] >= ends[0] + ends[2] / 2 - starts[2] / 2) && (starts[0] <= ends[0] + ends[2]) && (starts[1] <= ends[1] + ends[3]))
/*      */     {
/*  969 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] - 20);
/*  970 */       this.B = (ends[0] - 20 + "," + (starts[1] - 20));
/*  971 */       this.C = (ends[0] - 20 + "," + (ends[1] + ends[3] + 20));
/*  972 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + 20));
/*  973 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*  974 */     } else if ((starts[0] >= ends[0] + ends[2]) && (starts[1] <= ends[1] + ends[3]))
/*      */     {
/*  981 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] - 20);
/*  982 */       this.B = ((ends[0] + ends[2] + starts[0]) / 2 + "," + (starts[1] - 20));
/*  983 */       this.C = ((ends[0] + ends[2] + starts[0]) / 2 + "," + (ends[1] + ends[3] + 20));
/*  984 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + 20));
/*  985 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*      */     }
/*      */     else
/*      */     {
/*  989 */       int value = Math.abs(starts[0] + starts[2] / 2 - ends[0] - ends[2] / 2);
/*  990 */       int subLength = getNodesSubLength(nodeType, endNodeType);
/*  991 */       if ((value >= 0) && (value <= subLength)) {
/*  992 */         this.str = "";
/*      */       }
/*      */       else
/*      */       {
/*  998 */         this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, (ends[1] + ends[3] + starts[1]) / 2);
/*  999 */         this.B = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + starts[1]) / 2);
/* 1000 */         this.str = (this.A + ";" + this.B);
/*      */       }
/*      */     }
/* 1003 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String getPoint_y_1_x_0(int[] starts, int[] ends, String nodeType)
/*      */   {
/* 1019 */     if ((starts[0] <= ends[0] - starts[2] / 2) && (starts[1] <= ends[1] + ends[3] / 2 - starts[3]))
/*      */     {
/* 1023 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, ends[1] + ends[3] / 2);
/* 1024 */       this.str = this.A;
/* 1025 */     } else if ((starts[0] <= ends[0] - starts[2]) && (starts[1] >= ends[1] + ends[3] / 2 - starts[3]))
/*      */     {
/* 1031 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] + starts[3] + 20);
/* 1032 */       this.B = ((starts[0] + starts[2] + ends[0]) / 2 + 12 + "," + (starts[1] + starts[3] + 20));
/* 1033 */       this.C = ((starts[0] + starts[2] + ends[0]) / 2 + 12 + "," + (ends[1] + ends[3] / 2));
/* 1034 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/* 1035 */     } else if ((starts[0] >= ends[0] - ends[2] / 2) && (starts[1] <= ends[1] - starts[3]))
/*      */     {
/* 1041 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, (starts[1] + starts[3] + ends[1]) / 2);
/* 1042 */       this.B = (ends[0] - 20 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/* 1043 */       this.C = (ends[0] - 20 + "," + (ends[1] + ends[3] / 2));
/* 1044 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/* 1045 */     } else if ((starts[0] >= ends[0] - ends[2]) && (starts[1] >= ends[1] - starts[3]) && (starts[1] <= ends[1] + ends[3] - starts[3]))
/*      */     {
/* 1051 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, ends[1] + ends[3] + 20);
/* 1052 */       this.B = (ends[0] - 20 + "," + (ends[1] + ends[3] + 20));
/* 1053 */       this.C = (ends[0] - 20 + "," + (ends[1] + ends[3] / 2));
/* 1054 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*      */     }
/*      */     else
/*      */     {
/* 1061 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] + starts[3] + 20);
/* 1062 */       this.B = (ends[0] - 20 + "," + (starts[1] + starts[3] + 20));
/* 1063 */       this.C = (ends[0] - 20 + "," + (ends[1] + ends[3] / 2));
/* 1064 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*      */     }
/* 1066 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String getPoint_y_1_x_1(int[] starts, int[] ends, String nodeType)
/*      */   {
/* 1078 */     if ((starts[0] >= ends[0] + ends[2] - starts[2] / 2) && (starts[1] <= ends[1] + ends[3] / 2 - starts[3]))
/*      */     {
/* 1082 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, ends[1] + ends[3] / 2);
/* 1083 */       this.str = this.A;
/* 1084 */     } else if ((starts[0] >= ends[0] + ends[2]) && (starts[0] >= ends[1] + ends[3] / 2 - starts[3]))
/*      */     {
/* 1090 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] + starts[3] + 20);
/* 1091 */       this.B = ((ends[0] + ends[2] + starts[0]) / 2 + "," + (starts[1] + starts[3] + 20));
/* 1092 */       this.C = ((ends[0] + ends[2] + starts[0]) / 2 + "," + (ends[1] + ends[3] / 2));
/* 1093 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/* 1094 */     } else if ((starts[0] <= starts[0] + starts[2]) && (starts[1] >= ends[1] + ends[3] - starts[3]))
/*      */     {
/* 1100 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] + starts[3] + 20);
/* 1101 */       this.B = (ends[0] + ends[2] + 20 + "," + (starts[1] + starts[3] + 20));
/* 1102 */       this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] / 2));
/* 1103 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/* 1104 */     } else if ((starts[0] <= ends[0] + ends[2]) && (starts[1] >= ends[1] - starts[3]) && (starts[1] <= ends[1] + ends[3] - starts[3]))
/*      */     {
/* 1110 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, ends[1] + ends[3] + 20);
/* 1111 */       this.B = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] + 20));
/* 1112 */       this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] / 2));
/* 1113 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*      */     }
/*      */     else
/*      */     {
/* 1120 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, (starts[1] + starts[3] + ends[1]) / 2);
/* 1121 */       this.B = (ends[0] + ends[2] + 20 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/* 1122 */       this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] / 2));
/* 1123 */       this.str = (this.A + ";" + this.B + ";" + this.C);
/*      */     }
/* 1125 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String getPoint_y_1_y_0(int[] starts, int[] ends, String nodeType, String endNodeType)
/*      */   {
/* 1138 */     if (starts[1] <= ends[1] - starts[3])
/*      */     {
/* 1143 */       int value = Math.abs(starts[0] + starts[2] / 2 - ends[0] - ends[2] / 2);
/* 1144 */       int subLength = getNodesSubLength(nodeType, endNodeType);
/* 1145 */       if ((value >= 0) && (value <= subLength)) {
/* 1146 */         this.str = "";
/*      */       }
/*      */       else
/*      */       {
/* 1152 */         this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, (starts[1] + starts[3] + ends[1]) / 2);
/* 1153 */         this.B = (ends[0] + ends[2] / 2 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/* 1154 */         this.str = (this.A + ";" + this.B);
/*      */       }
/* 1156 */     } else if ((starts[0] >= ends[0] + ends[2]) && (starts[1] >= ends[1] - starts[3]))
/*      */     {
/* 1163 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] + starts[3] + 20);
/* 1164 */       this.B = ((ends[0] + ends[2] + starts[0]) / 2 + "," + (starts[1] + starts[3] + 20));
/* 1165 */       this.C = ((ends[0] + ends[2] + starts[0]) / 2 + "," + (ends[1] - 20));
/* 1166 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] - 20));
/* 1167 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/* 1168 */     } else if ((starts[0] >= ends[0] - starts[2]) && (starts[0] <= ends[0] + ends[2] / 2 - starts[2] / 2) && (starts[1] >= ends[1] + ends[3] - starts[3]))
/*      */     {
/* 1175 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] + starts[3] + 20);
/* 1176 */       this.B = (ends[0] + ends[2] + 20 + "," + (starts[1] + starts[3] + 20));
/* 1177 */       this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] - 20));
/* 1178 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] - 20));
/* 1179 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/* 1180 */     } else if ((starts[0] >= ends[0] + ends[2] / 2 - starts[2] / 2) && (starts[0] <= ends[0] + ends[2]) && (starts[1] >= ends[1] + ends[3] - starts[3]))
/*      */     {
/* 1187 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] + starts[3] + 20);
/* 1188 */       this.B = (ends[0] - 20 + "," + (starts[1] + starts[3] + 20));
/* 1189 */       this.C = (ends[0] - 20 + "," + (ends[1] - 20));
/* 1190 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] - 20));
/* 1191 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/* 1192 */     } else if ((starts[0] >= ends[0] - ends[2]) && (starts[0] <= ends[0] + ends[2] / 2 - starts[2] / 2) && (starts[1] >= ends[1] - ends[3]) && (starts[1] <= ends[1] + ends[3] - starts[3]))
/*      */     {
/* 1199 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, ends[1] + ends[3] + 20);
/* 1200 */       this.B = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] + 20));
/* 1201 */       this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] - 20));
/* 1202 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] - 20));
/* 1203 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/* 1204 */     } else if ((starts[0] >= ends[0] + ends[2] / 2 - starts[2] / 2) && (starts[0] <= ends[0] + ends[2]) && (starts[1] >= ends[1] - starts[3]) && (starts[1] <= ends[1] + ends[3] - starts[3]))
/*      */     {
/* 1211 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, ends[1] + ends[3] + 20);
/* 1212 */       this.B = (ends[0] - 20 + "," + (ends[1] + ends[3] + 20));
/* 1213 */       this.C = (ends[0] - 20 + "," + (ends[1] - 20));
/* 1214 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] - 20));
/* 1215 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*      */     }
/*      */     else
/*      */     {
/* 1223 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] + starts[3] + 20);
/* 1224 */       this.B = ((starts[0] + starts[2] + ends[0]) / 2 + 12 + "," + (starts[1] + starts[3] + 20));
/* 1225 */       this.C = ((starts[0] + starts[2] + ends[0]) / 2 + 12 + "," + (ends[1] - 20));
/* 1226 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] - 20));
/* 1227 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*      */     }
/* 1229 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String getPoint_y_1_y_1(int[] starts, int[] ends, String nodeType)
/*      */   {
/* 1241 */     if (((starts[0] >= ends[0] + ends[2] / 2) || (starts[0] <= ends[0] + ends[2] / 2 - starts[2])) && (starts[1] >= ends[1] + ends[3] - starts[3]))
/*      */     {
/* 1246 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] + starts[3] + 20);
/* 1247 */       this.B = (ends[0] + ends[2] / 2 + "," + (starts[1] + starts[3] + 20));
/* 1248 */       this.str = (this.A + ";" + this.B);
/* 1249 */     } else if ((starts[0] >= ends[0] + ends[2] / 2 - starts[2]) && (starts[0] <= ends[0] + ends[2] / 2 - starts[2] / 2) && (starts[1] >= ends[1] + ends[3] - starts[3]))
/*      */     {
/* 1256 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] + starts[3] + 20);
/* 1257 */       this.B = (starts[0] + starts[2] + 20 + "," + (starts[1] + starts[3] + 20));
/* 1258 */       this.C = (starts[0] + starts[2] + 20 + "," + (ends[1] + ends[3] + starts[1]) / 2);
/* 1259 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + starts[1]) / 2);
/* 1260 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/* 1261 */     } else if ((starts[0] >= ends[0] + ends[2] / 2 - starts[2] / 2) && (starts[0] <= ends[0] + ends[0]) && (starts[1] >= ends[1] + ends[3] - starts[3]))
/*      */     {
/* 1268 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, starts[1] + starts[3] + 20);
/* 1269 */       this.B = (starts[0] - 20 + "," + (starts[1] + starts[3] + 20));
/* 1270 */       this.C = (starts[0] - 20 + "," + (ends[1] + ends[3] + starts[1]) / 2);
/* 1271 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + starts[1]) / 2);
/* 1272 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/* 1273 */     } else if ((starts[0] >= ends[0] - starts[2] / 2) && (starts[0] <= ends[0] + ends[2] - starts[2] / 2) && (starts[1] > ends[1] - starts[3]) && (starts[1] < ends[1] + ends[3] - starts[3]))
/*      */     {
/* 1278 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, ends[1] + ends[3] + 20);
/* 1279 */       this.B = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + 20));
/* 1280 */       this.str = (this.A + ";" + this.B);
/* 1281 */     } else if ((starts[0] >= ends[0] - starts[2] / 2) && (starts[0] < ends[0] + ends[2] / 2 - starts[2] / 2) && (starts[1] <= ends[1] - starts[3]))
/*      */     {
/* 1288 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, (starts[1] + starts[3] + ends[1]) / 2);
/* 1289 */       this.B = (ends[0] - 20 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/* 1290 */       this.C = (ends[0] - 20 + "," + (ends[1] + ends[3] + 20));
/* 1291 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + 20));
/* 1292 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/* 1293 */     } else if ((starts[0] >= ends[0] + ends[2] / 2 - starts[2] / 2) && (starts[0] <= ends[0] + ends[2] - starts[2] / 2) && (starts[1] <= ends[1] - starts[3]))
/*      */     {
/* 1300 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, (starts[1] + starts[3] + ends[1]) / 2);
/* 1301 */       this.B = (ends[0] + ends[2] + 20 + "," + (starts[1] + starts[3] + ends[1]) / 2);
/* 1302 */       this.C = (ends[0] + ends[2] + 20 + "," + (ends[1] + ends[3] + 20));
/* 1303 */       this.D = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + 20));
/* 1304 */       this.str = (this.A + ";" + this.B + ";" + this.C + ";" + this.D);
/*      */     }
/*      */     else
/*      */     {
/* 1311 */       this.A = addStartNodeLength(nodeType, starts[0] + starts[2] / 2, ends[1] + ends[3] + 20);
/* 1312 */       this.B = (ends[0] + ends[2] / 2 + "," + (ends[1] + ends[3] + 20));
/* 1313 */       this.str = (this.A + ";" + this.B);
/*      */     }
/* 1315 */     return this.str;
/*      */   }
/*      */ 
/*      */   private String addStartNodeLength(String nodeType, int xLength, int yLength)
/*      */   {
/* 1329 */     String str = xLength + "," + yLength;
/* 1330 */     if ((nodeType != null) && (nodeType.equals("StartEvent")))
/* 1331 */       str = xLength + 6 + "," + yLength;
/* 1332 */     return str;
/*      */   }
/*      */ 
/*      */   private int getNodesSubLength(String startNode, String endNode)
/*      */   {
/* 1344 */     return startNode.equals(endNode) ? 0 : 7;
/*      */   }
/*      */ }

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.bpm.ILog.helper.AddTransition
 * JD-Core Version:    0.6.0
 */
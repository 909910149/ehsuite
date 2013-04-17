 package com.htsoft.oa.action.admin;
 
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.util.JsonUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.admin.GoodsApply;
 import com.htsoft.oa.model.admin.OfficeGoods;
 import com.htsoft.oa.model.info.ShortMessage;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.admin.GoodsApplyService;
 import com.htsoft.oa.service.admin.OfficeGoodsService;
 import com.htsoft.oa.service.info.ShortMessageService;
 import flexjson.JSONSerializer;
 import flexjson.transformer.DateTransformer;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class GoodsApplyAction extends BaseAction
 {
   private static short PASS_APPLY = 2;
   private static short NOTPASS_APPLY = 3;
 
   @Resource
   private GoodsApplyService goodsApplyService;
   private GoodsApply goodsApply;
 
   @Resource
   private ShortMessageService shortMessageService;
 
   @Resource
   private OfficeGoodsService officeGoodsService;
   private Long applyId;
 
   public Long getApplyId() { return this.applyId; }
 
   public void setApplyId(Long applyId)
   {
     this.applyId = applyId;
   }
 
   public GoodsApply getGoodsApply() {
     return this.goodsApply;
   }
 
   public void setGoodsApply(GoodsApply goodsApply) {
     this.goodsApply = goodsApply;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.goodsApplyService.getAll(filter);
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "applyDate" });
     buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
     buff.append("}");
     this.jsonString = buff.toString();
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.goodsApplyService.remove(new Long(id));
       }
     }
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String get()
   {
     GoodsApply goodsApply = (GoodsApply)this.goodsApplyService.get(this.applyId);
     StringBuffer sb = new StringBuffer("{success:true,data:");
     JSONSerializer serializer = new JSONSerializer();
     serializer.transform(new DateTransformer("yyyy-MM-dd"), new String[] { "applyDate" });
     sb.append(serializer.exclude(new String[] { "class" }).serialize(goodsApply));
     sb.append("}");
     setJsonString(sb.toString());
     return "success";
   }
 
   public String save()
   {
     if (this.goodsApply.getApplyId() != null) {
       GoodsApply orgGoodsApply = (GoodsApply)this.goodsApplyService.get(this.goodsApply.getApplyId());
       try
       {
         BeanUtil.copyNotNullProperties(orgGoodsApply, this.goodsApply);
 
         if (orgGoodsApply.getApprovalStatus().shortValue() == PASS_APPLY) {
           OfficeGoods officeGoods = (OfficeGoods)this.officeGoodsService.get(orgGoodsApply.getGoodsId());
           Integer con = orgGoodsApply.getUseCounts();
           Integer least = Integer.valueOf(officeGoods.getStockCounts().intValue() - con.intValue());
           if (least.intValue() < 0) {
             setJsonString("{success:false,message:'库存不足!'}");
             return "success";
           }
           Long receiveId = orgGoodsApply.getUserId();
           String content = "你申请的办公用品为" + officeGoods.getGoodsName() + "已经通过审批，请查收";
           this.shortMessageService.save(AppUser.SYSTEM_USER, receiveId.toString(), content, ShortMessage.MSG_TYPE_SYS);
           officeGoods.setStockCounts(least);
           this.officeGoodsService.save(officeGoods);
         }
         this.goodsApplyService.save(orgGoodsApply);
       }
       catch (Exception e) {
         e.printStackTrace();
       }
     } else {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-SSSS");
       this.goodsApply.setApplyNo("GA" + sdf.format(new Date()));
       this.goodsApplyService.save(this.goodsApply);
     }
 
     setJsonString("{success:true}");
     return "success";
   }
 }


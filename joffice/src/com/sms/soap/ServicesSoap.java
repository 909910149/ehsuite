package com.sms.soap;

import java.rmi.Remote;
import java.rmi.RemoteException;

public abstract interface ServicesSoap extends Remote
{
  public abstract SendStatus directSend(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt1, String paramString7, int paramInt2);

  public abstract StockDetails directGetStockDetails(String paramString1, String paramString2, String paramString3)
    throws RemoteException;

  public abstract RepliedSMS directFetchSMS(String paramString1, String paramString2, String paramString3)
    throws RemoteException;

  public abstract CommonReturn passwordChange(String paramString1, String paramString2, String paramString3, String paramString4)
    throws RemoteException;
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.sms.soap.ServicesSoap
 * JD-Core Version:    0.6.0
 */
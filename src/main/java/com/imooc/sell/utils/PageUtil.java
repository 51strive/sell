package com.imooc.sell.utils;

public class PageUtil {

    public static String GetPageNavigation(String pagename,int PageNo,int PageCount,String param)
    {
        StringBuilder PageControl = new StringBuilder();
        if (PageNo - 1 == 0)
            PageControl.append("<li class=\"disabled\"><a href=\"#\">首页</a></li>"); //首页
        else
            PageControl.append("<li> <a href=\"" + pagename + "?page=" + (PageNo - 1) + param + "\">上一页</a></li>");
        int pageturn = PageNo - (PageNo > 5 ? 5 : PageNo);
        for (int i = 1; i < 11; i++)
        {
            if (pageturn + i > PageCount) break;
            if (pageturn + i == PageNo)
                PageControl.append(" <li class=\"active\"><a href=\"#\"> " + (pageturn + i) + "</a></li>");
            else
                PageControl.append("<li> <a href=\"" + pagename + "?page=" + (pageturn + i) + param + "\">" + (pageturn + i) + "</a></li>");
        }
        if (PageNo + 1 > PageCount)
            PageControl.append("<li class=\"disabled\"><a href=\"#\">下一页</a></li>"); //最后一页
        else
            PageControl.append(" <li> <a href=\"" + pagename + "?page=" + (PageNo + 1) + param + "\">下一页</a></li>  ");
        return PageControl.toString();
    }
}

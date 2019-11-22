package com.helper.func;

public class PageDet {
	public static String pagefun(String uid){
		int i = Integer.parseInt(uid);
		String base;
		if(i == 1){
			base = "func/users.jsp";
		}else if(i == 2){
			base = "func/article.jsp";
		}else if(i == 3){
			base = "func/comment.jsp";
		}else if(i == 4){
			base = "func/bullent.jsp";
		}else{
			base = null;
		}
		return base;
	}

}

package com.helper.func;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.db.UseJdbc;

public class PageTools {
	//�����û���������һҳ
	public static int userpageup(int begin, int end) throws SQLException{
		ResultSet urs = null;
		urs = UseJdbc.usermun();
		String all = "";
		while(urs.next()){
			all = urs.getString("total");
		}
		int all_i = Integer.parseInt(all);
		System.out.println(all_i);
		if(end == all_i-1 || end > all_i-1){
			begin = begin;
			end = end;
			System.out.println("û��");
			return 1;
		}else{
			begin = begin + 10;
			end = end + 10;
			System.out.println(begin);
			System.out.println(end);
			System.out.println("����");
			return 2;
		}
		
	}
	//���������������һҳ
	public static int articlepageup(int begin, int end) throws SQLException{
		ResultSet ars = null;
		ars = UseJdbc.artmun();
		String all = "";
		while(ars.next()){
			all = ars.getString("total");
		}
		int all_i = Integer.parseInt(all);
		System.out.println(all_i);
		if(end == all_i-1 || end > all_i-1){
			begin = begin;
			end = end;
			System.out.println("û��");
			return 1;
		}else{
			begin = begin + 10;
			end = end + 10;
			System.out.println(begin);
			System.out.println(end);
			System.out.println("����");
			return 2;
		}
	}
	//��������ҳ������һҳ
	public static int fastpageup(int begin, int end) throws SQLException{
		ResultSet frs = null;
		frs = UseJdbc.fastmun();
		String all = "";
		while(frs.next()){
			all = frs.getString("total");
		}
		int all_i = Integer.parseInt(all);
		System.out.println(all_i);
		if(end == all_i-1 || end > all_i-1){
			begin = begin;
			end = end;
			System.out.println("û��");
			return 1;
		}else{
			begin = begin + 10;
			end = end + 10;
			System.out.println(begin);
			System.out.println(end);
			System.out.println("����");
			return 2;
		}
	}
	//��������ҳ������һҳ
	public static int comup(int begin, int end) throws SQLException{
		ResultSet crs = null;
		crs = UseJdbc.commun();
		String all = "";
		while(crs.next()){
			all = crs.getString("total");
		}
		int all_i = Integer.parseInt(all);
		System.out.println(all_i);
		if(end == all_i-1 || end > all_i-1){
			begin = begin;
			end = end;
			System.out.println("û��");
			return 1;
		}else{
			begin = begin + 10;
			end = end + 10;
			System.out.println(begin);
			System.out.println(end);
			System.out.println("����");
			return 2;
		}
	}
	//������ǰһҳ
	public static int down(int begin, int end){
		if(begin == 0 && begin == end - 9){
			begin = 0;
			end = 9;
			System.out.println("û��");
			return 1;
		}else if(begin == end - 9){
			if(begin%10==0){
				begin = begin - 10;
				end = end - 10;
				System.out.println(begin);
				System.out.println(end);
				System.out.println("����");
				return 2;
			}else{
				begin = 0;
				end = 9;
				System.out.println(begin);
				System.out.println(end);
				System.out.println("����");
				return 3;
			}
		}else{
			begin = 0;
			end = 9;
			System.out.println(begin);
			System.out.println(end);
			System.out.println("����");
			return 3;
		}
	}
}

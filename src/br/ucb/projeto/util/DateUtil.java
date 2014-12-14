package br.ucb.projeto.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtil {
	public static String stringFromDate(Calendar date){
		return date.get(Calendar.YEAR)+"-"+stringIntComZerosEsquerda((date.get(Calendar.MONTH)+1))+"-"+stringIntComZerosEsquerda(date.get(Calendar.DAY_OF_MONTH));
	}
	
	public static GregorianCalendar dateFromString(String dateString){
		String array[] = dateString.split("-");
		GregorianCalendar dataEvento = new GregorianCalendar();
		dataEvento.set(Calendar.YEAR,Integer.parseInt(array[0]));
		dataEvento.set(Calendar.MONTH,Integer.parseInt(array[1])-1);
		dataEvento.set(Calendar.DAY_OF_MONTH,Integer.parseInt(array[2]));
		return dataEvento;
	}
	public static String stringIntComZerosEsquerda(int inteiro){
		String str;
		if(inteiro < 10){
			str = String.format("0%d",inteiro);
		}else{
			str = String.format("%d",inteiro);
		}
		return str;
	}
	
}

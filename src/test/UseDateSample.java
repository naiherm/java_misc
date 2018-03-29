package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * 日付型のサンプルコード
 */
public class UseDateSample {

	public static void main(String[] args) {
		
		// java.util.Date 型と java.sql.Date 型は似ているけれど異なります。
		// この型の違いをうまく使い分けることで、日付に関する操作ができます。
		// 二つの型を 統合するには long型の値 を使用します。
		// 	※ 1970年1月1日0時0分0秒 からの経過ミリ秒 
		
		System.out.println("-----------------");
		checkDate();
		
		System.out.println("-----------------");
		checkCalender();
	}
	
	/**
	 * SQL日付型を 表示するための操作について
	 * 	java.util.Date 型との互換操作
	 * 	文字列フォーマットの調整
	 */
	public static void checkDate() {
		
		// SQL日付型(データには時刻も含まれる)
		java.sql.Date date = new java.sql.Date(0);
		System.out.println(date.toString());

		// 現在時刻
		long now = System.currentTimeMillis();
		// 日付と時刻
		java.sql.Date nowD = new java.sql.Date(now);
		java.sql.Time nowT = new java.sql.Time(now);
		System.out.println(nowD.toString());
		System.out.println(nowT.toString());

		// 表示文字列の整形
		// ※SimpleDateFormat のポップアップ表記参照
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分ss秒");
        System.out.println(fmt.format(date.getTime()));
        System.out.println(fmt.format(nowD.getTime()));
        
        // 表示フォーマットの変更
        fmt.applyPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(fmt.format(date.getTime()));
        System.out.println(fmt.format(nowD.getTime()));
		
        
        // 読み取り用のフォーマットを変更
        fmt.applyPattern("yyyy-MM-dd HH:mm:ss");
    	// 変換に失敗する可能性があるので try{}catch(){}
        try{
        	// フォーマットに従った文字列 を java.util 日付型 に変換してみる。
        	java.util.Date test = fmt.parse("2010-02-02 23:59:59");
        	System.out.println( test.toString() );

        	// java.util 日付型 → java.sql 日付型への変換 
        	java.sql.Date testSql = new java.sql.Date( test.getTime() );
        	System.out.println( testSql.toString() );
        	
        	
        }catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * カレンダークラスを使用した 日付の操作
	 */
	public static void checkCalender() {
		
		// カレンダーオブジェクトの作成。念のため日本ロケールで。
		Calendar cal = Calendar.getInstance( Locale.JAPAN );
		
		// 現在時刻取得
		long now = System.currentTimeMillis();
		// カレンダーに 計測すべき日付を設定(long型版を使用)
		cal.setTimeInMillis(now);
		
		// 年・月・日
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		System.out.println( "年：" + year );
		System.out.println( "月：" + month );
		System.out.println( "日：" + day );
		
		// 曜日
		// http://docs.oracle.com/javase/jp/8/docs/api/java/util/Formatter.html#dt
		final String WEEK_FULL = "%1$tA";
		final String WEEK_NORM = "%1$ta";
    	System.out.println( String.format(WEEK_FULL, cal) );
    	System.out.println( String.format(WEEK_NORM, cal) );
    	
		int week = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println( "曜日：" + week );
		
//		// switch文整形してませんが....
//		switch( week ){
//		case Calendar.SUNDAY:	System.out.println("日");	break;
//		case Calendar.MONDAY:	System.out.println("月");	break;
//		case Calendar.TUESDAY:	System.out.println("火");	break;
//		case Calendar.WEDNESDAY:System.out.println("水");	break;
//		case Calendar.THURSDAY:	System.out.println("木");	break;
//		case Calendar.FRIDAY:	System.out.println("金");	break;
//		case Calendar.SATURDAY:	System.out.println("土");	break;
//		}
	}


}

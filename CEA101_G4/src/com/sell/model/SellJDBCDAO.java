package com.sell.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sell.model.*;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Sell;
import util.Util;

public class SellJDBCDAO implements SellDAO_interface{

	private static final String INSERT_STMT = 
		"INSERT INTO SELLER_MEMBER(SELL_MEM_ID, SELL_MEM_ACCOUNT, SELL_MEM_PWD, SELL_MEM_NAME, SELL_MEM_BIRTH, SELL_MEM_TEL, SELL_ROOM_NAME, SELL_MEM_ADDRESS, SELL_LATITUDE, SELL_LONGITUD, SELL_MEM_MAIL, SELL_MEM_ID_NUMBER, SELL_ACC_STATUS, SELL_GENDER)"
			+" VALUES ('SELL' || LPAD(SELL_SEQ.NEXTVAL, 3, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM SELLER_MEMBER";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM SELLER_MEMBER WHERE SELL_MEM_ID = ?";
	private static final String DELETE = 
		"DELETE FROM SELLER_MEMBER WHERE SELL_MEM_ID = ?";
	private static final String UPDATE = 
		"UPDATE SELLER_MEMBER SET SELL_MEM_ACCOUNT=?, SELL_MEM_PWD=?, SELL_MEM_NAME=?, SELL_MEM_BIRTH=?, SELL_MEM_TEL=?, SELL_ROOM_NAME=?, SELL_MEM_ADDRESS=?, SELL_LATITUDE=?, SELL_LONGITUD=?, SELL_MEM_MAIL=?, SELL_MEM_ID_NUMBER=?, SELL_ACC_STATUS=?, SELL_GENDER=? WHERE SELL_MEM_ID = ?";
	private static final String UPDATE_INFO = 
		"UPDATE SELLER_MEMBER SET SELL_MEM_NAME=?, SELL_MEM_TEL=?, SELL_ROOM_NAME=?, SELL_MEM_ADDRESS=?, SELL_LATITUDE=?, SELL_LONGITUD=?, SELL_MEM_MAIL=? WHERE SELL_MEM_ID = ?";
	public static String UPDATE_PWD = 
		"UPDATE SELLER_MEMBER SET SELL_MEM_PWD=? WHERE SELL_MEM_ID = ?";	

	public static String getTest (String colName) {
		return "SELECT * FROM SELLER_MEMBER WHERE " + colName + " = ?";
	}
	
	@Override
	public void insert(SellVO sellVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, sellVO.getSellMemAccount());
			pstmt.setString(2, sellVO.getSellMemPwd());
			pstmt.setString(3, sellVO.getSellMemName());
			pstmt.setDate(4, sellVO.getSellMemBirth());
			pstmt.setString(5, sellVO.getSellMemTel());
			pstmt.setString(6, sellVO.getSellRoomName());
			pstmt.setString(7, sellVO.getSellMemAddress());
			pstmt.setDouble(8, sellVO.getSellLatitude());
			pstmt.setDouble(9, sellVO.getSellLongitud());
			pstmt.setString(10, sellVO.getSellMemMail());
			pstmt.setString(11, sellVO.getSellMemIdNumber());
			pstmt.setInt(12, sellVO.getSellAccStatus());
			pstmt.setInt(13, sellVO.getSellGender());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(SellVO sellVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, sellVO.getSellMemAccount());
			pstmt.setString(2, sellVO.getSellMemPwd());
			pstmt.setString(3, sellVO.getSellMemName());
			pstmt.setDate(4, sellVO.getSellMemBirth());
			pstmt.setString(5, sellVO.getSellMemTel());
			pstmt.setString(6, sellVO.getSellRoomName());
			pstmt.setString(7, sellVO.getSellMemAddress());
			pstmt.setDouble(8, sellVO.getSellLatitude());
			pstmt.setDouble(9, sellVO.getSellLongitud());
			pstmt.setString(10, sellVO.getSellMemMail());
			pstmt.setString(11, sellVO.getSellMemIdNumber());
			pstmt.setInt(12, sellVO.getSellAccStatus());
			pstmt.setInt(13, sellVO.getSellGender());
			pstmt.setString(14, sellVO.getSellMemId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	public void updateInfo(SellVO sellVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_INFO);

			pstmt.setString(1, sellVO.getSellMemName());
			pstmt.setString(2, sellVO.getSellMemTel());
			pstmt.setString(3, sellVO.getSellRoomName());
			pstmt.setString(4, sellVO.getSellMemAddress());
			pstmt.setDouble(5, sellVO.getSellLatitude());
			pstmt.setDouble(6, sellVO.getSellLongitud());
			pstmt.setString(7, sellVO.getSellMemMail());
			pstmt.setString(8, sellVO.getSellMemId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}	

	public void updatePwd(SellVO sellVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_PWD);

			pstmt.setString(1, sellVO.getSellMemPwd());
			pstmt.setString(2, sellVO.getSellMemId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	


	@Override
	public void delete(String sellMemId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, sellMemId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public SellVO findByPrimaryKey(String sellMemId) {

		SellVO sellVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, sellMemId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sellVO = new SellVO();
				sellVO.setSellMemId(rs.getString("SELL_MEM_ID"));
				sellVO.setSellMemAccount(rs.getString("SELL_MEM_ACCOUNT"));
				sellVO.setSellMemPwd(rs.getString("SELL_MEM_PWD"));
				sellVO.setSellMemName(rs.getString("SELL_MEM_NAME"));
				sellVO.setSellMemBirth(rs.getDate("SELL_MEM_BIRTH"));
				sellVO.setSellMemTel(rs.getString("SELL_MEM_TEL"));
				sellVO.setSellRoomName(rs.getString("SELL_ROOM_NAME"));
				sellVO.setSellMemAddress(rs.getString("SELL_MEM_ADDRESS"));
				sellVO.setSellLatitude(rs.getDouble("SELL_LATITUDE"));
				sellVO.setSellLongitud(rs.getDouble("SELL_LONGITUD"));
				sellVO.setSellMemMail(rs.getString("SELL_MEM_MAIL"));
				sellVO.setSellMemIdNumber(rs.getString("SELL_MEM_ID_NUMBER"));
				sellVO.setSellAccStatus(rs.getInt("SELL_ACC_STATUS"));
				sellVO.setSellGender(rs.getInt("SELL_GENDER"));
				sellVO.setSellJointime(rs.getTimestamp("SELL_JOINTIME"));				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return sellVO;
	}
	
	@Override
	public SellVO findByCol(String colName, String compareValue) {
		SellVO sellVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			
//			System.out.println("sql = " + getTest(colName));
			pstmt = con.prepareStatement(getTest(colName));
//			System.out.println("compareValue = " + compareValue);

			pstmt.setString(1,  compareValue);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				sellVO = new SellVO();
				sellVO.setSellMemId(rs.getString("SELL_MEM_ID"));
				sellVO.setSellMemAccount(rs.getString("SELL_MEM_ACCOUNT"));
				sellVO.setSellMemPwd(rs.getString("SELL_MEM_PWD"));
				sellVO.setSellMemName(rs.getString("SELL_MEM_NAME"));
				sellVO.setSellMemBirth(rs.getDate("SELL_MEM_BIRTH"));
				sellVO.setSellMemTel(rs.getString("SELL_MEM_TEL"));
				sellVO.setSellRoomName(rs.getString("SELL_ROOM_NAME"));
				sellVO.setSellMemAddress(rs.getString("SELL_MEM_ADDRESS"));
				sellVO.setSellLatitude(rs.getDouble("SELL_LATITUDE"));
				sellVO.setSellLongitud(rs.getDouble("SELL_LONGITUD"));
				sellVO.setSellMemMail(rs.getString("SELL_MEM_MAIL"));
				sellVO.setSellMemIdNumber(rs.getString("SELL_MEM_ID_NUMBER"));
				sellVO.setSellAccStatus(rs.getInt("SELL_ACC_STATUS"));
				sellVO.setSellGender(rs.getInt("SELL_GENDER"));
				sellVO.setSellJointime(rs.getTimestamp("SELL_JOINTIME"));
			}


			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return sellVO;
	}
	
	

	@Override
	public List<SellVO> getAll() {
		List<SellVO> list = new ArrayList<SellVO>();
		SellVO sellVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sellVO = new SellVO();
				sellVO.setSellMemId(rs.getString("SELL_MEM_ID"));
				sellVO.setSellMemAccount(rs.getString("SELL_MEM_ACCOUNT"));
				sellVO.setSellMemPwd(rs.getString("SELL_MEM_PWD"));
				sellVO.setSellMemName(rs.getString("SELL_MEM_NAME"));
				sellVO.setSellMemBirth(rs.getDate("SELL_MEM_BIRTH"));
				sellVO.setSellMemTel(rs.getString("SELL_MEM_TEL"));
				sellVO.setSellRoomName(rs.getString("SELL_ROOM_NAME"));
				sellVO.setSellMemAddress(rs.getString("SELL_MEM_ADDRESS"));
				sellVO.setSellLatitude(rs.getDouble("SELL_LATITUDE"));
				sellVO.setSellLongitud(rs.getDouble("SELL_LONGITUD"));
				sellVO.setSellMemMail(rs.getString("SELL_MEM_MAIL"));
				sellVO.setSellMemIdNumber(rs.getString("SELL_MEM_ID_NUMBER"));
				sellVO.setSellAccStatus(rs.getInt("SELL_ACC_STATUS"));
				sellVO.setSellGender(rs.getInt("SELL_GENDER"));
				sellVO.setSellJointime(rs.getTimestamp("SELL_JOINTIME"));				
				list.add(sellVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public List<SellVO> getAll(Map<String, String[]> map) {
		List<SellVO> list = new ArrayList<SellVO>();
		SellVO sellVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			String finalSQL = "SELECT * FROM SELLER_MEMBER "
		          + jdbcUtil_CompositeQuery_Sell.get_WhereCondition(map)
		          + "ORDER BY SELL_MEM_ID";
			pstmt = con.prepareStatement(finalSQL);
//			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				sellVO = new SellVO();
				sellVO.setSellMemId(rs.getString("SELL_MEM_ID"));
				sellVO.setSellMemAccount(rs.getString("SELL_MEM_ACCOUNT"));
				sellVO.setSellMemPwd(rs.getString("SELL_MEM_PWD"));
				sellVO.setSellMemName(rs.getString("SELL_MEM_NAME"));
				sellVO.setSellMemBirth(rs.getDate("SELL_MEM_BIRTH"));
				sellVO.setSellMemTel(rs.getString("SELL_MEM_TEL"));
				sellVO.setSellRoomName(rs.getString("SELL_ROOM_NAME"));
				sellVO.setSellMemAddress(rs.getString("SELL_MEM_ADDRESS"));
				sellVO.setSellLatitude(rs.getDouble("SELL_LATITUDE"));
				sellVO.setSellLongitud(rs.getDouble("SELL_LONGITUD"));
				sellVO.setSellMemMail(rs.getString("SELL_MEM_MAIL"));
				sellVO.setSellMemIdNumber(rs.getString("SELL_MEM_ID_NUMBER"));
				sellVO.setSellAccStatus(rs.getInt("SELL_ACC_STATUS"));
				sellVO.setSellGender(rs.getInt("SELL_GENDER"));
				sellVO.setSellJointime(rs.getTimestamp("SELL_JOINTIME"));				
				list.add(sellVO); // Store the row in the list
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {

		SellJDBCDAO dao = new SellJDBCDAO();
		// 新增
		SellVO sellVO1 = new SellVO();
		
//		sellVO1.setSellMemId("SELL080");
//		sellVO1.setSellMemAccount("newAccount");
//		sellVO1.setSellMemPwd("newPassword");
//		sellVO1.setSellMemName("Sell的名字啦");
//		sellVO1.setSellMemBirth(Date.valueOf("1999-09-09"));
//		sellVO1.setSellMemTel("0912333444");
//		sellVO1.setSellRoomName("民宿名稱的啦");
//		sellVO1.setSellMemAddress("地址要能對應到Google api");
//		sellVO1.setSellLatitude(13.22244456);
//		sellVO1.setSellLongitud(13.22244456);
//		sellVO1.setSellMemMail("tibame@gmail.com");
//		sellVO1.setSellMemIdNumber("C178902888");
//		sellVO1.setSellAccStatus(0);
//		sellVO1.setSellGender(0);
//		sellVO1.setSellJointime(new Timestamp(System.currentTimeMillis()));	
//		
//		dao.insert(sellVO1);
		
//		INSERT INTO SELLER_MEMBER
//		(SELL_MEM_ID, SELL_MEM_ACCOUNT, SELL_MEM_PWD, SELL_MEM_NAME, SELL_MEM_BIRTH, SELL_MEM_TEL, SELL_ROOM_NAME,
//		SELL_MEM_ADDRESS, SELL_MEM_MAIL, SELL_MEM_ID_NUMBER, SELL_ACC_STATUS, SELL_GENDER)
//		VALUES('SELL' || LPAD(SELL_SEQ.NEXTVAL, 3, '0'), 'SELL01', '123456', '張三', TO_DATE('1981-11-17','YYYY-MM-DD'), '0987654321', 'SP民宿', '中央復興緯育', 
//		'SEAFOOD@GMAIL.COM', 'A123456789', '1', '1');
	

//		// 修改
//		SellVO sellVO2 = new SellVO();
//		sellVO2.setSellMemId("SELL002");
//		sellVO2.setSellMemAccount("updateAccount");
//		sellVO2.setSellMemPwd("updatePassword");
//		sellVO2.setSellMemName("updateSell的名字啦");
//		sellVO2.setSellMemBirth((Date) Date.valueOf("2000-01-01"));
//		sellVO2.setSellMemTel("0911999888");
//		sellVO2.setSellRoomName("update民宿名稱的啦");
//		sellVO2.setSellMemAddress("update新的地址JDBC");
//		sellVO2.setSellLatitude(13.22244456);
//		sellVO2.setSellLongitud(123.22244456);
//		sellVO2.setSellMemMail("updateTibame@gmail.com");
//		sellVO2.setSellMemIdNumber("C17234333");
//		sellVO2.setSellAccStatus(1);
//		sellVO2.setSellGender(1);
//		sellVO2.setSellJointime(new Timestamp(System.currentTimeMillis()));	
//		dao.update(sellVO2);
//
//		// 刪除
//		dao.delete("SELL004");
//
//		// 查詢
//		SellVO sellVO3 = dao.findByPrimaryKey("SELL003");
//		System.out.print(sellVO3.getSellMemId() + ",");
//		System.out.print(sellVO3.getSellMemAccount() + ",");
//		System.out.print(sellVO3.getSellMemPwd() + ",");
//		System.out.print(sellVO3.getSellMemName() + ",");
//		System.out.print(sellVO3.getSellMemBirth() + ",");
//		System.out.print(sellVO3.getSellMemTel() + ",");
//		System.out.print(sellVO3.getSellRoomName() + ",");
//		System.out.print(sellVO3.getSellMemAddress() + ",");
//		System.out.print(sellVO3.getSellLatitude() + ",");
//		System.out.print(sellVO3.getSellLongitud() + ",");
//		System.out.print(sellVO3.getSellMemMail() + ",");
//		System.out.print(sellVO3.getSellMemIdNumber() + ",");
//		System.out.print(sellVO3.getSellAccStatus() + ",");
//		System.out.print(sellVO3.getSellGender() + ",");
//		System.out.println(sellVO3.getSellJointime());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<SellVO> list = dao.getAll();
//		for (SellVO room : list) {
//			System.out.print(room.getSellMemId() + ",");
//			System.out.print(room.getSellMemAccount() + ",");
//			System.out.print(room.getSellMemPwd() + ",");
//			System.out.print(room.getSellMemName() + ",");
//			System.out.print(room.getSellMemBirth() + ",");
//			System.out.print(room.getSellMemTel() + ",");
//			System.out.print(room.getSellRoomName() + ",");
//			System.out.print(room.getSellMemAddress() + ",");
//			System.out.print(room.getSellLatitude() + ",");
//			System.out.print(room.getSellLongitud() + ",");
//			System.out.print(room.getSellMemMail() + ",");
//			System.out.print(room.getSellMemIdNumber() + ",");
//			System.out.print(room.getSellAccStatus() + ",");
//			System.out.print(room.getSellGender() + ",");
//			System.out.println(room.getSellJointime());
//			System.out.println();
//		}
		
		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

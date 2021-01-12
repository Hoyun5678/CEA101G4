package com.sell.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Sell;

public class SellDAO implements SellDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
	public void updateInfo(SellVO sellVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
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
	public void updatePwd(SellVO sellVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PWD);

			pstmt.setString(1, sellVO.getSellMemPwd());
			pstmt.setString(2, sellVO.getSellMemId());

			pstmt.executeUpdate();

			// Handle any driver errors
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
			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, sellMemId);

			pstmt.executeUpdate();

			// Handle any driver errors
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
			con = ds.getConnection();

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
			con = ds.getConnection();

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

	@Override
	public List<SellVO> getAll(Map<String, String[]> map) {
		List<SellVO> list = new ArrayList<SellVO>();
		SellVO sellVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
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

	@Override
	public SellVO findByCol(String colName, String compareValue) {
		SellVO sellVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getTest(colName));

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

}

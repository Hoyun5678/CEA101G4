package com.activity_period.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Activity_Period;

public class ActivityPeriodDAO implements ActivityPeriodDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
		
		private static final String INSERT_STMT = "INSERT INTO ACTIVITY_PERIOD(ACT_PERIOD_ID, ACT_ID, ACT_SIGN_START, ACT_SIGN_END, "
				+ "ACT_PERIOD_START, ACT_PERIOD_END, ACT_UP_LIMIT, ACT_LOW_LIMIT, ACT_CUR_PRICE,ACT_PERIOD_STATUS, ACT_SIGN_SUM)"
				+"VALUES('AP' || LPAD(AP_SEQ.NEXTVAL, 3, '0'), ?,?,?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = "SELECT ACT_PERIOD_ID, ACT_ID, ACT_SIGN_START, ACT_SIGN_END," 
							+ "ACT_PERIOD_START, ACT_PERIOD_END, ACT_UP_LIMIT, ACT_LOW_LIMIT, ACT_CUR_PRICE,ACT_PERIOD_STATUS, ACT_SIGN_SUM FROM ACTIVITY_PERIOD";
		private static final String GET_ONE_STMT = "SELECT ACT_PERIOD_ID, ACT_ID, ACT_SIGN_START, ACT_SIGN_END," 
							+ "ACT_PERIOD_START, ACT_PERIOD_END, ACT_UP_LIMIT, ACT_LOW_LIMIT, ACT_CUR_PRICE,ACT_PERIOD_STATUS, ACT_SIGN_SUM FROM ACTIVITY_PERIOD where ACT_PERIOD_ID = ?";
		private static final String GET_ALL_BY_ACT_ID = "SELECT ACT_PERIOD_ID, ACT_ID, ACT_SIGN_START, ACT_SIGN_END," 
				+ "ACT_PERIOD_START, ACT_PERIOD_END, ACT_UP_LIMIT, ACT_LOW_LIMIT, ACT_CUR_PRICE,ACT_PERIOD_STATUS, ACT_SIGN_SUM FROM ACTIVITY_PERIOD where ACT_ID = ?";
//		private static final String GET_Mem_ByDeptno_STMT = "SELECT empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal,comm,deptno FROM emp2 where deptno = ? order by empno";
		private static final String GET_LIST_BY_ACT_PERIOD_START = "SELECT ACT_PERIOD_ID, ACT_ID, ACT_SIGN_START, ACT_SIGN_END," 
				+ "ACT_PERIOD_START, ACT_PERIOD_END, ACT_UP_LIMIT,"
				+"ACT_LOW_LIMIT, ACT_CUR_PRICE,ACT_PERIOD_STATUS, ACT_SIGN_SUM FROM ACTIVITY_PERIOD"+
				" WHERE ACT_PERIOD_START>to_timestamp(?, 'yyyy-mm-dd hh24:mi:ss')"
						+ " and ACT_PERIOD_START<to_timestamp(?, 'yyyy-mm-dd hh24:mi:ss')";
//		private static final String DELETE_ACTIVITY_TYPE = "DELETE FROM ACTIVITY_TYPE where deptno = ?";
//		private static final String DELETE = "DELETE FROM MEMBER1 where MEM_ID = ?";	

		private static final String UPDATE = "UPDATE  ACTIVITY_PERIOD set ACT_ID=?, ACT_SIGN_START=?, ACT_SIGN_END=?,"
				+ "ACT_PERIOD_START=?, ACT_PERIOD_END=?, ACT_UP_LIMIT=?, ACT_LOW_LIMIT=?, ACT_CUR_PRICE=?,ACT_PERIOD_STATUS=?, ACT_SIGN_SUM=? where ACT_PERIOD_ID=?";
		private static final String UPDATE_ACTPERIOD_STATUS = "UPDATE  ACTIVITY_PERIOD set ACT_PERIOD_STATUS=? where ACT_PERIOD_ID=?";
		@Override
		public void insert(ActivityPeriodVO actperVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, actperVO.getAct_id());
				pstmt.setTimestamp(2, actperVO.getAct_sign_start());
				pstmt.setTimestamp(3, actperVO.getAct_sign_end());
				pstmt.setTimestamp(4, actperVO.getAct_period_start());
				pstmt.setTimestamp(5, actperVO.getAct_period_end());
				pstmt.setInt(6, actperVO.getAct_up_limit());
				pstmt.setInt(7, actperVO.getAct_low_limit());
				pstmt.setDouble(8, actperVO.getAct_cur_price());
				pstmt.setInt(9, actperVO.getAct_period_status());
				pstmt.setInt(10, actperVO.getAct_sign_sum());
				
				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
		
		public List<ActivityPeriodVO> getListByActPeriodStart(String act_period_start_on_date) {
			List<ActivityPeriodVO>list=new ArrayList<ActivityPeriodVO>();
			ActivityPeriodVO actperVO=null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;                                                        
//			:00.0
//			
//			new java.sql.Timestamp(	java.sql.Date.valueOf(req.getParameter("act_sign_start")).getTime());
//			java.sql.Timestamp act_period_start = java.sql.Timestamp.valueOf(req.getParameter("act_period_start") + "00:00:00.0");
//			
			long fromToLong = java.sql.Timestamp.valueOf(act_period_start_on_date+" 00:00:00.0").getTime();
			long fromToLong2 = java.sql.Timestamp.valueOf(act_period_start_on_date+" 23:59:59.0").getTime();
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_LIST_BY_ACT_PERIOD_START);
				String dayStart=new Timestamp(fromToLong).toString().replace(".0", "");
				String dayEnd=new Timestamp(fromToLong2).toString().replace(".0", "");
				pstmt.setString(1, dayStart);
				pstmt.setString(2, dayEnd);
				
				
				rs=pstmt.executeQuery();
				while(rs.next()) {
					actperVO=new ActivityPeriodVO();
					actperVO.setAct_period_id(rs.getString("act_period_id"));
					actperVO.setAct_id(rs.getString("act_id"));
					actperVO.setAct_sign_start(rs.getTimestamp("act_sign_start"));
					actperVO.setAct_sign_end(rs.getTimestamp("act_sign_end"));
					actperVO.setAct_period_start(rs.getTimestamp("act_period_end"));
					actperVO.setAct_period_end(rs.getTimestamp("act_period_end"));
					actperVO.setAct_up_limit(rs.getInt("act_up_limit"));
					actperVO.setAct_low_limit(rs.getInt("act_low_limit"));
					actperVO.setAct_cur_price(rs.getDouble("act_cur_price"));
					actperVO.setAct_period_status(rs.getInt("act_period_status"));
					actperVO.setAct_sign_sum(rs.getInt("act_sign_sum"));
					list.add(actperVO);
				}
				
				

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
			return list;
		}
		
		@Override
		public List<ActivityPeriodVO> getAllActPerByActId(String act_id) {
			List<ActivityPeriodVO>list=new ArrayList<ActivityPeriodVO>();
			ActivityPeriodVO actperVO=null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_BY_ACT_ID);
				pstmt.setString(1, act_id);
				
				rs=pstmt.executeQuery();
				while(rs.next()) {
					actperVO=new ActivityPeriodVO();
					actperVO.setAct_period_id(rs.getString("act_period_id"));
					actperVO.setAct_id(rs.getString("act_id"));
					actperVO.setAct_sign_start(rs.getTimestamp("act_sign_start"));
					actperVO.setAct_sign_end(rs.getTimestamp("act_sign_end"));
					actperVO.setAct_period_start(rs.getTimestamp("act_period_end"));
					actperVO.setAct_period_end(rs.getTimestamp("act_period_end"));
					actperVO.setAct_up_limit(rs.getInt("act_up_limit"));
					actperVO.setAct_low_limit(rs.getInt("act_low_limit"));
					actperVO.setAct_cur_price(rs.getDouble("act_cur_price"));
					actperVO.setAct_period_status(rs.getInt("act_period_status"));
					actperVO.setAct_sign_sum(rs.getInt("act_sign_sum"));
					list.add(actperVO);
				}
				
				

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
			return list;
		}

			
		
		@Override
		public void update(ActivityPeriodVO actperVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, actperVO.getAct_id());
				pstmt.setTimestamp(2, actperVO.getAct_sign_start());
				pstmt.setTimestamp(3, actperVO.getAct_sign_end());
				pstmt.setTimestamp(4, actperVO.getAct_period_start());
				pstmt.setTimestamp(5, actperVO.getAct_period_end());
				pstmt.setInt(6, actperVO.getAct_up_limit());
				pstmt.setInt(7, actperVO.getAct_low_limit());
				pstmt.setDouble(8, actperVO.getAct_cur_price());
				pstmt.setInt(9, actperVO.getAct_period_status());
				pstmt.setInt(10, actperVO.getAct_sign_sum());
				pstmt.setString(11, actperVO.getAct_period_id());
				
				pstmt.executeUpdate();
				
				

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
		public ActivityPeriodVO findByPrimaryKey(String act_period_id) {
			ActivityPeriodVO actperVO=null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				pstmt.setString(1, act_period_id);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					actperVO=new ActivityPeriodVO();
					actperVO.setAct_period_id(rs.getString("act_period_id"));
					actperVO.setAct_id(rs.getString("act_id"));
					actperVO.setAct_sign_start(rs.getTimestamp("act_sign_start"));
					actperVO.setAct_sign_end(rs.getTimestamp("act_sign_end"));
					actperVO.setAct_period_start(rs.getTimestamp("act_period_end"));
					actperVO.setAct_period_end(rs.getTimestamp("act_period_end"));
					actperVO.setAct_up_limit(rs.getInt("act_up_limit"));
					actperVO.setAct_low_limit(rs.getInt("act_low_limit"));
					actperVO.setAct_cur_price(rs.getDouble("act_cur_price"));
					actperVO.setAct_period_status(rs.getInt("act_period_status"));
					actperVO.setAct_sign_sum(rs.getInt("act_sign_sum"));
					
				}
				
				

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
			return actperVO;
		}
		@Override
		public void upDateActPerStatus(String act_period_id, Integer act_period_status) {
			ActivityPeriodVO actperVO=null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_ACTPERIOD_STATUS);
				pstmt.setInt(1, act_period_status);
				pstmt.setString(2, act_period_id);
				rs=pstmt.executeQuery();
				
					
				
				
				

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
		public void delete(String act_period_id) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public ActivityPeriodVO findbyaddress(ActivityPeriodVO act_period_id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<ActivityPeriodVO> getAll() {
			List<ActivityPeriodVO>list=new ArrayList<ActivityPeriodVO>();
			ActivityPeriodVO actperVO=null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				
				rs=pstmt.executeQuery();
				while(rs.next()) {
					actperVO=new ActivityPeriodVO();
					actperVO.setAct_period_id(rs.getString("act_period_id"));
					actperVO.setAct_id(rs.getString("act_id"));
					actperVO.setAct_sign_start(rs.getTimestamp("act_sign_start"));
					actperVO.setAct_sign_end(rs.getTimestamp("act_sign_end"));
					actperVO.setAct_period_start(rs.getTimestamp("act_period_end"));
					actperVO.setAct_period_end(rs.getTimestamp("act_period_end"));
					actperVO.setAct_up_limit(rs.getInt("act_up_limit"));
					actperVO.setAct_low_limit(rs.getInt("act_low_limit"));
					actperVO.setAct_cur_price(rs.getDouble("act_cur_price"));
					actperVO.setAct_period_status(rs.getInt("act_period_status"));
					actperVO.setAct_sign_sum(rs.getInt("act_sign_sum"));
					list.add(actperVO);
				}
				
				

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
			return list;
		}
		public List<ActivityPeriodVO> getAll(Map<String, String[]> map) {
			List<ActivityPeriodVO> list = new ArrayList<ActivityPeriodVO>();
			ActivityPeriodVO actperVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
				
				con = ds.getConnection();
				String finalSQL = "SELECT ACT_PERIOD_ID,PE.ACT_ID,ACT_SIGN_START,ACT_SIGN_END,ACT_PERIOD_START,"
						+ "ACT_PERIOD_END,ACT_UP_LIMIT,ACT_LOW_LIMIT,ACT_CUR_PRICE,ACT_PERIOD_STATUS,"
						+ "ACT_SIGN_SUM,ACT_NAME,ACT_DES,ACT_ADD,TY.ACT_TYPE_ID,ACT_NAME" + 
						" FROM ACTIVITY_PERIOD PE" + 
						" JOIN ACTIVITY_PRODUCT PR ON (PE.ACT_ID=PR.ACT_ID)" + 
						" JOIN ACTIVITY_TYPE TY ON (TY.ACT_TYPE_ID=PR.ACT_TYPE_ID)" + 
			           jdbcUtil_CompositeQuery_Activity_Period.get_WhereCondition(map)
			          + " ORDER BY ACT_PERIOD_ID";
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("●●finalSQL(by DAO) = "+finalSQL);
				rs = pstmt.executeQuery();
		
				while (rs.next()) {
					actperVO=new ActivityPeriodVO();
					actperVO.setAct_period_id(rs.getString("act_period_id"));
					actperVO.setAct_id(rs.getString("act_id"));
					actperVO.setAct_sign_start(rs.getTimestamp("act_sign_start"));
					actperVO.setAct_sign_end(rs.getTimestamp("act_sign_end"));
					actperVO.setAct_period_start(rs.getTimestamp("act_period_end"));
					actperVO.setAct_period_end(rs.getTimestamp("act_period_end"));
					actperVO.setAct_up_limit(rs.getInt("act_up_limit"));
					actperVO.setAct_low_limit(rs.getInt("act_low_limit"));
					actperVO.setAct_cur_price(rs.getDouble("act_cur_price"));
					actperVO.setAct_period_status(rs.getInt("act_period_status"));
					actperVO.setAct_sign_sum(rs.getInt("act_sign_sum"));
					list.add(actperVO);
				}
		
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
			ActivityPeriodJDBCDAO dao=new ActivityPeriodJDBCDAO();
			// 新增 ====================================
//			ActivityPeriodVO actperVO = new ActivityPeriodVO();
//			actperVO.setAct_id("ACT001");
//			actperVO.setAct_sign_start(java.sql.Timestamp.valueOf("2020-11-10 15:00:00"));
//			actperVO.setAct_sign_end(java.sql.Timestamp.valueOf("2020-11-13 15:00:00"));
//			actperVO.setAct_period_start(java.sql.Timestamp.valueOf("2020-11-20 15:00:00"));
//			actperVO.setAct_period_end(java.sql.Timestamp.valueOf("2020-11-20 17:00:00"));
//			actperVO.setAct_up_limit(20);
//			actperVO.setAct_low_limit(5);
//			actperVO.setAct_cur_price(2000D);
//			actperVO.setAct_period_status(1);
//			actperVO.setAct_sign_sum(16);
//			dao.insert(actperVO);
//			//修改活動期別狀態=============================
			dao.upDateActPerStatus("AP001", 2);
			//修改=====================================
//			ActivityPeriodVO actperVO2 = new ActivityPeriodVO();
//			actperVO2.setAct_id("ACT003");
//			actperVO2.setAct_sign_start(java.sql.Timestamp.valueOf("2020-03-10 15:00:00"));
//			actperVO2.setAct_sign_end(java.sql.Timestamp.valueOf("2020-04-13 15:00:00"));
//			actperVO2.setAct_period_start(java.sql.Timestamp.valueOf("2020-06-20 15:00:00"));
//			actperVO2.setAct_period_end(java.sql.Timestamp.valueOf("2020-07-20 17:00:00"));
//			actperVO2.setAct_up_limit(222);
//			actperVO2.setAct_low_limit(101);
//			actperVO2.setAct_cur_price(36600D);
//			actperVO2.setAct_period_status(1);
//			actperVO2.setAct_sign_sum(300);
//			actperVO2.setAct_period_id("AP007");
//			dao.update(actperVO2);
			//查詢 getByPK=============================
//			ActivityPeriodVO actperVO3 = dao.findByPrimaryKey("AP003");
//			System.out.println(actperVO3.getAct_id());
//			System.out.println(actperVO3.getAct_sign_start());
//			System.out.println(actperVO3.getAct_sign_end());
//			System.out.println(actperVO3.getAct_period_start());
//			System.out.println(actperVO3.getAct_period_end());
//			System.out.println(actperVO3.getAct_up_limit());
//			System.out.println(actperVO3.getAct_low_limit());
//			System.out.println(actperVO3.getAct_cur_price());
//			System.out.println(actperVO3.getAct_period_status());
//			System.out.println(actperVO3.getAct_sign_sum());
//			//查詢 getAll================================
//			List<ActivityPeriodVO>list=dao.getAll();
//			for(ActivityPeriodVO aActPer:list) {
//				
//				System.out.println(aActPer.getAct_id());
//				System.out.println(aActPer.getAct_sign_start());
//				System.out.println(aActPer.getAct_sign_end());
//				System.out.println(aActPer.getAct_period_start());
//				System.out.println(aActPer.getAct_period_end());
//				System.out.println(aActPer.getAct_up_limit());
//				System.out.println(aActPer.getAct_low_limit());
//				System.out.println(aActPer.getAct_cur_price());
//				System.out.println(aActPer.getAct_period_status());
//				System.out.println(aActPer.getAct_sign_sum());
//				System.out.println("=============================");
//			}
			
			List<ActivityPeriodVO>list=dao.getListByActPeriodStart("2020-06-01");
			for(ActivityPeriodVO aActPer:list) {
				
				System.out.println(aActPer.getAct_id());
				System.out.println(aActPer.getAct_sign_start());
				System.out.println(aActPer.getAct_sign_end());
				System.out.println(aActPer.getAct_period_start());
				System.out.println(aActPer.getAct_period_end());
				System.out.println(aActPer.getAct_up_limit());
				System.out.println(aActPer.getAct_low_limit());
				System.out.println(aActPer.getAct_cur_price());
				System.out.println(aActPer.getAct_period_status());
				System.out.println(aActPer.getAct_sign_sum());
				System.out.println("=============================");
			}
			
			
			
			
			
		}

		



	}


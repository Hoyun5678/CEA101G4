package com.reply.model;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import java.util.*;
import com.reply.model.ReplyVO;

public class ReplyDAO implements ReplyDAO_interface{
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G4";
	String passwd = "CEA101G4";
	
	private static final String INSERT_STMT = 
			"INSERT INTO reply(reply_id, act_period_id, mem_id, reply_content, reply_visible) "
			+ "VALUES ('RPL' || LPAD(RPL_SEQ.NEXTVAL, 3, '0'), ?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM reply where reply_id = ?";
	private static final String UPDATE = 
			"UPDATE reply set act_period_id=?, mem_id=?, reply_content=?, reply_visible=?"
			+ " where reply_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT act_period_id, mem_id,reply_content, reply_time, reply_visible "
			+ "FROM reply where reply_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT reply_id, act_period_id, mem_id,reply_content, reply_time, reply_visible "
					+ "FROM reply order by reply_id";

	@Override
	public void insert(ReplyVO replyVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, replyVO.getActPeriodId());
			pstmt.setString(2, replyVO.getMemId());
			pstmt.setString(3, replyVO.getReplyContent());
			pstmt.setInt(4, replyVO.getReplyVisible());
			
			pstmt.executeUpdate();

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
	public void update(ReplyVO replyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, replyVO.getActPeriodId());
			pstmt.setString(2, replyVO.getMemId());
			pstmt.setString(3, replyVO.getReplyContent());
//			pstmt.setDate(4, replyVO.getReplyTime());
			pstmt.setInt(4, replyVO.getReplyVisible());
			pstmt.setString(5, replyVO.getReplyId());
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
	public void delete(String replyId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, replyId);

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
	public ReplyVO findByPrimaryKey(String replyId) {
		ReplyVO replyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, replyId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				replyVO = new ReplyVO();
				replyVO.setReplyId(replyId);
				replyVO.setActPeriodId(rs.getString("act_period_id"));
				replyVO.setMemId(rs.getString("mem_id"));
				replyVO.setReplyContent(rs.getString("reply_content"));
				replyVO.setReplyTime(rs.getTimestamp("reply_time"));
				replyVO.setReplyVisible(rs.getInt("reply_visible"));
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
		return replyVO;
		
		
	}

	@Override
	public List<ReplyVO> getAll() {
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		ReplyVO replyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				replyVO = new ReplyVO();
				replyVO.setReplyId(rs.getString("reply_id"));
				replyVO.setActPeriodId(rs.getString("act_period_id"));
				replyVO.setMemId(rs.getString("mem_id"));
				replyVO.setReplyContent(rs.getString("reply_content"));
				replyVO.setReplyTime(rs.getTimestamp("reply_time"));
				replyVO.setReplyVisible(rs.getInt("reply_visible"));
				list.add(replyVO);
				// Store the row in the list
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
	

}

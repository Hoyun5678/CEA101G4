package com.souvenir_photo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class SouvenirPhotoDAO implements SouvenirPhotoDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}



//	private static final String INSERT_STMT =
//			"INSERT INTO SOUVENIR_PHOTO(SOU_PHOTO_ID, SOU_ID, SOU_PHOTO,SOU_PHOTO_CONTENT)"
//			+ " VALUES ('SOUPH' || LPAD(SPHO_SEQ.NEXTVAL, 3, '0'),?,?,?)";
	private static final String UPDATE = 
 	"UPDATE SOUVENIR_PHOTO set SOU_ID=?, SOU_PHOTO=?, SOU_PHOTO_CONTENT=? where SOU_PHOTO_ID=?";
	private static final String DELETE = "DELETE FROM SOUVENIR_PHOTO where SOU_PHOTO_ID = ?";
	private static final String GET_ONE_STMT =
			"SELECT SOU_PHOTO_ID, SOU_ID, SOU_PHOTO, SOU_PHOTO_CONTENT FROM SOUVENIR_PHOTO where SOU_PHOTO_ID = ?";
	private static final String GET_ALL_STMT = 
			"SELECT SOU_PHOTO_ID, SOU_ID, SOU_PHOTO, SOU_PHOTO_CONTENT FROM SOUVENIR_PHOTO order by SOU_PHOTO_ID";
	private static final String GET_BYSOUID = 
			"SELECT * FROM SOUVENIR_PHOTO WHERE SOU_ID = ?";
	private static final String INSERT_STMT2 =
			"INSERT INTO SOUVENIR_PHOTO(SOU_PHOTO_ID, SOU_ID,SOU_PHOTO)"
			+ " VALUES ('SOUPH' || LPAD(SPHO_SEQ.NEXTVAL, 3, '0'),?,?)";
	
	@Override
	public void insert(SouvenirPhotoVO souphVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT2);

			pstmt.setBytes(2, souphVO.getSou_photo());
			pstmt.setString(1, souphVO.getSou_id());
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

	@Override
	public void update(SouvenirPhotoVO souphVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, souphVO.getSou_id());
			pstmt.setBytes(2, souphVO.getSou_photo());
			pstmt.setString(3, souphVO.getSou_photo_content());
			pstmt.setString(4, souphVO.getSou_photo_id());

			

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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

	@Override
	public void delete(String getSou_photo_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, getSou_photo_id);
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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

	@Override
	public SouvenirPhotoVO findByPrimaryKey(String getSou_photo_id) {

		SouvenirPhotoVO souphVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, getSou_photo_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				souphVO = new SouvenirPhotoVO();
				
				souphVO.setSou_photo_id(rs.getString("sou_photo_id"));
				souphVO.setSou_id(rs.getString("sou_id"));
				souphVO.setSou_photo(rs.getBytes("sou_photo"));
				souphVO.setSou_photo_content(rs.getString("sou_photo_content"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return souphVO;
	}

	@Override
	public List<SouvenirPhotoVO> getAll() {
		List<SouvenirPhotoVO> list = new ArrayList<SouvenirPhotoVO>();
		SouvenirPhotoVO souphVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				souphVO = new SouvenirPhotoVO();
				
				souphVO.setSou_photo_id(rs.getString("sou_photo_id"));
				souphVO.setSou_id(rs.getString("sou_id"));
				souphVO.setSou_photo(rs.getBytes("sou_photo"));
				souphVO.setSou_photo_content(rs.getString("sou_photo_content"));
			
				list.add(souphVO);
			}

			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	public List<SouvenirPhotoVO> getBySouId(String sou_id){
		List<SouvenirPhotoVO> list = new ArrayList<SouvenirPhotoVO>();

		SouvenirPhotoVO souphVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYSOUID);
			pstmt.setString(1,sou_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				souphVO = new SouvenirPhotoVO();
				
				souphVO.setSou_photo_id(rs.getString("sou_photo_id"));
				souphVO.setSou_id(rs.getString("sou_id"));
				souphVO.setSou_photo(rs.getBytes("sou_photo"));
				souphVO.setSou_photo_content(rs.getString("sou_photo_content"));
				list.add(souphVO);
			}

			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	
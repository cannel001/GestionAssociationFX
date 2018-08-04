package com.objis.gestassociation.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.objis.gestassociation.domaine.Divers;

/**
 * Classe Divers Dao
 * @author Seka Cannel Ulrich Evrard
 *
 */
public class DiversDao extends Dao<Divers, Long> {
	
	//les requetes
	private final static String createStatement="insert into divers (corpsFormulaire,date,etatDivers,id,lieu,motif,typeFormulaire) values (?,?,?,?,?,?,?)";
	private final static String updateStatement="update divers set corpsFormulaire=?,date=?,lieu=?,motif=?,typeFormulaire=? where id=? and etatDivers='ACTIF'";
	private final static String readOneStatement="select * from divers where id=? and etatDivers='ACTIF'";
	private final static String deleteStatement="update divers set etatDivers='SUPPRIMER' where id=?";
	private final static String readAllStatement="select * from divers where etatDivers='ACTIF'";
	
	private int executeQuery=-1;

	@Override
	public Boolean create(Divers t) {
		
		try {
			
			ps=createStatement(createStatement);
			
			ps.setString(1, t.getCorpsFormalire());
			ps.setDate(2, Date.valueOf(t.getDate()));
			ps.setString(3, t.getEtatDivers());
			ps.setLong(4, t.getId());
			ps.setString(5, t.getLieu());
			ps.setString(6, t.getMotif());
			ps.setString(7, t.getTypeFormulaire());
			
			executeQuery=ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return executeQuery>0;
		
	}

	@Override
	public Divers readOne(Long pk) {
		
		Divers divers=null;
		
		try {
			
			ps=createStatement(readOneStatement);
			
			ps.setLong(1, pk);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				divers=new Divers(rs.getDate("date").toLocalDate(),rs.getString("lieu"),rs.getString("typeFormulaire"),rs.getString("motif"),rs.getString("corpsFormulaire"),rs.getLong("id"),rs.getString("etatDivers"));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return divers;
	}

	@Override
	public Boolean update(Divers t) {
		
		try {
			
			ps=createStatement(updateStatement);
			
			ps.setString(1, t.getCorpsFormalire());
			ps.setDate(2, Date.valueOf(t.getDate()));
			ps.setString(3, t.getLieu());
			ps.setString(4, t.getMotif());
			ps.setString(5, t.getTypeFormulaire());
			ps.setLong(6, t.getId());
			
			executeQuery=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return executeQuery>0;
	}

	@Override
	public Boolean delete(Long pk) {
		
		try {
			
			ps=createStatement(deleteStatement);
			
			ps.setLong(1, pk);
			
			executeQuery=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return executeQuery>0;
	}

	@Override
	public List<Divers> readAll() {
		
		List<Divers> maList=new LinkedList<>();
		
		try {
			
			ps=createStatement(readAllStatement);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				maList.add(new Divers(rs.getDate("date").toLocalDate(),rs.getString("lieu"),rs.getString("typeFormulaire"),rs.getString("motif"),rs.getString("corpsFormulaire"),rs.getLong("id"),rs.getString("etatDivers")));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return maList;
	}

}

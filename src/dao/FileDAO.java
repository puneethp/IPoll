package dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class FileDAO extends BaseHibernate {
	public FileContent getFileContent(String fileId)
	{
		FileContent file =null;
		Session session = null;
		Criteria ctr;
		try
		{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(FileContent.class);
			ctr.add(Restrictions.idEq(fileId));
			if(ctr.list().size()>0)
				file = (FileContent)ctr.list().get(0);
		}catch(HibernateException e)
		{
			System.err.println("Error: FileDao method: getFileContent()");
		}finally
		{
			if(session!=null)
				session.close();
		}
		return file;
	}
	
	public FileInfo getFileInfo(String fileId)
	{
		Session session =null;
		Criteria ctr = null;
		FileInfo file = null;
		try{
			session = getSessionFactory().openSession();
			ctr = session.createCriteria(FileInfo.class);
			ctr.add(Restrictions.idEq(fileId));
			if(ctr.list().size()>0)
				file= (FileInfo) ctr.list().get(0);
		}catch(HibernateException e)
		{
			System.err.println("Error: FileDAO method: getFileInfo()");
			e.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return file;
	}
	
	public boolean writeFileInfo(FileInfo file)
	{
		Session session =null;
		Transaction trans = null;
		try
		{
			if(file==null)
				return false;
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(file);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			if(trans!=null)
				trans.rollback();
			System.err.println("Error: FileDAO method: writeFileInfo()");
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	public boolean deleteFile(String fileId,String id)
	{
		Session session =null;
		Transaction trans = null;
		FileInfo f = null;
		FileContent fc = null;
		try
		{
			if(fileId==null)
				return false;
			f = getFileInfo(fileId);
			fc = getFileContent(fileId);
			if(f.getUploadedBy().compareToIgnoreCase(id)!=0)
				return false;
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.delete(fc);
			session.delete(f);
			trans.commit();session.flush();
		}catch(HibernateException e)
		{
			if(trans!=null)
				trans.rollback();
			System.err.println("Error: FileDAO method: writeFileContent()");
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
	public boolean writeFileContent(FileContent file)
	{
		Session session =null;
		Transaction trans = null;
		try
		{
			if(file==null)
				return false;
			session = getSessionFactory().openSession();
			trans = session.beginTransaction();
			session.save(file);
			trans.commit();session.flush();
			return true;
		}catch(HibernateException e)
		{
			if(trans!=null)
				trans.rollback();
			System.err.println("Error: FileDAO method: writeFileContent()");
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return false;
	}
}

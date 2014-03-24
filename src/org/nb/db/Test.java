package org.nb.db;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.nb.mybatis.model.INB_Entity;
import org.nb.mybatis.model.NB_Entity;

public class Test {

	public static void main(String[] args) {
		SqlSession session = DBDispture.currentSession();
		try {
			//
			// INB_User iuser = session.getMapper(INB_User.class);
			// NB_User user = new NB_User();
			// user.setName("steven");
			// user.setPassword("nihao");
			// for (int i = 0; i < 1000; i++) {
			// NB_User nb_User = iuser.findUserByName(user);
			// System.out.println(nb_User.getEmail());
			// session.commit();
			//
			// }
			//
			// session.close();
			// NB_User nb_User = iuser.findUserByName(user);
			// System.out.println(nb_User.getEmail());
			INB_Entity inb_Entity = session.getMapper(INB_Entity.class);
			NB_Entity entity = new NB_Entity();
			entity.setNbContent("今天吃了一个鲸鱼");
			entity.setNbAuther(1);

			entity.setNbPubtime(new Date());
			// System.out.println(inb_Entity.release(entity));

			Map<String, Integer> params = new HashMap<String, Integer>();
			params.put("nbId", 3);
			params.put("nbStatus", 2);

			System.out.println(inb_Entity.verify(params));
			// System.out.println(inb_Entity.delete(2));
			session.commit();
		} finally {
			session.close();
		}
	}
};

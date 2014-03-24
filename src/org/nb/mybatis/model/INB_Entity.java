package org.nb.mybatis.model;

import java.util.List;
import java.util.Map;

public interface INB_Entity {
	public int release(NB_Entity nb_Entity);

	public int verify(Map<String, Integer> params);

	public int delete(Integer nb_id);

	public int getUpNum(Integer nb_id);

	public NB_Entity getEntity(Integer nb_id);

	public List<NB_Entity> getUserNBS(Map<String, Integer> params);

	public List<NB_Entity> getNBS(Map<String, Integer> params);

	public int setUpNum(Integer nb_id, Integer upNum);

	public int getDownNum(Integer nb_id);

	public int setDownNum(Integer nb_id, Integer upNum);

}

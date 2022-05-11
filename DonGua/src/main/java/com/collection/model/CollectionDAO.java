package com.collection.model;

import java.io.IOException;
import java.util.List;

public interface CollectionDAO {

	public void addCollection(CollectionVO vo);
	public void deleteCollection(Integer memberId, Integer carerId);
	public List<CollectionVO> findByMemId(Integer memId) throws IOException;
	public Integer findServiceTimes(Integer memberId, Integer carerId);
	public Integer isCollected(Integer memberId, Integer carerId);
}

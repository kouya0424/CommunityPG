package com.rugbyaholic.communityPG.common.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rugbyaholic.communityPG.common.Option;

@Mapper
public interface CodeRepository {

	public List<Option> getDepertmentCd();

	public List<Option> getPositionCd();

	public List<Option> getCode(long id);
}
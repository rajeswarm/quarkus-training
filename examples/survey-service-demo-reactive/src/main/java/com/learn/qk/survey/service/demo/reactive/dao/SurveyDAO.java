package com.learn.qk.survey.service.demo.reactive.dao;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public interface SurveyDAO<TDTO, TID> {
	Uni<TDTO> insert(TDTO dto);
	Uni<TDTO> getById(TID id);
	Multi<TDTO> getAll();
}

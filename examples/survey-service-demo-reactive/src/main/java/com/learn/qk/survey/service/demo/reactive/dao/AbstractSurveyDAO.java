package com.learn.qk.survey.service.demo.reactive.dao;

import javax.inject.Inject;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;

abstract class AbstractSurveyDAO<TDTO, TID> implements SurveyDAO<TDTO, TID> {

	@Inject
	protected MySQLPool mysqlClient;

	protected final String tableName;

	protected AbstractSurveyDAO(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public Multi<TDTO> getAll() {
		Uni<RowSet<Row>> rowSetUni = mysqlClient.query(String.format("SELECT * FROM %s", this.tableName)).execute();
		return rowSetUni.onItem().transformToMulti(rs -> Multi.createFrom().iterable(rs)).onItem()
				.transform(row -> convertRowToDTO(row));
	}

	protected abstract TDTO convertRowToDTO(Row row);
}

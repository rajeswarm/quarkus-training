package com.learn.qk.survey.service.demo.reactive.dao;

import javax.enterprise.context.ApplicationScoped;

import com.learn.qk.survey.service.demo.reactive.dto.TopicDTO;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;
import io.vertx.sqlclient.PropertyKind;

@ApplicationScoped
class TopicDAOImpl extends AbstractSurveyDAO<TopicDTO, Integer> {

	protected TopicDAOImpl() {
		super("TOPICS");
	}

	@Override
	public Uni<TopicDTO> insert(TopicDTO dto) {
		return mysqlClient
				.preparedQuery(String.format("INSERT INTO %s ( TOPIC_NAME, TOPIC_SEQUENCE) VALUES (?,?)", tableName))
				.execute(Tuple.of(dto.getTopicName(), dto.getTopicSequence())).onItem().transform(rows -> {
					// set inserted id
					@SuppressWarnings("unchecked")
					Long lastInsertedId = (Long) rows.getDelegate()
							.property(PropertyKind.create("last-inserted-id", Long.class));
					dto.setTopicId(lastInsertedId.intValue());
					return dto;
				});
	}

	@Override
	public Uni<TopicDTO> getById(Integer id) {
		return mysqlClient.preparedQuery(String.format("SELECT * FROM %s WHERE TOPIC_ID = ?", tableName))
				.execute(Tuple.of(id)).onItem().transformToMulti(rs -> Multi.createFrom().iterable(rs)).toUni().onItem()
				.transform(row -> convertRowToDTO(row));
	}

	@Override
	protected TopicDTO convertRowToDTO(Row row) {
		TopicDTO topicDTO = new TopicDTO();
		topicDTO.setTopicId(row.getInteger("TOPIC_ID"));
		topicDTO.setTopicName(row.getString("TOPIC_NAME"));
		topicDTO.setTopicSequence(row.getInteger("TOPIC_SEQUENCE"));

		return topicDTO;
	}
}

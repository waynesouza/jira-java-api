package com.example.demo.mapper;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.example.demo.dto.IssueResponseDTO;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.Objects;

public interface CustomConverter {

    Converter<Issue, IssueResponseDTO> converterToIssueDto = new Converter<Issue, IssueResponseDTO>() {
        public IssueResponseDTO convert(MappingContext<Issue, IssueResponseDTO> context) {
            Issue source = context.getSource();
            IssueResponseDTO target = new IssueResponseDTO();
            target.setId(source.getId().toString());
            target.setKey(source.getKey());
            target.setDescription(source.getSummary());
            if (Objects.nonNull(source.getStatus())) {
                target.setStatus(source.getStatus().getName());
            }
            if (Objects.nonNull(source.getIssueType())) {
                target.setType(source.getIssueType().getName());
            }
            return target;
        }
    };

}

package de.ait.artcake.utils;

import de.ait.artcake.handler.RestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageRequestsUtil {

    @Value("${spring.application.order.page.size}")
    private Integer pageSize;

    public PageRequest getPageRequest(Integer pageNumber, String orderByField, Boolean desc, List<String> sortFields) {

        if (orderByField != null && !orderByField.equals("")) {

            checkField(sortFields, orderByField);

            Sort.Direction direction = Sort.Direction.ASC;

            if (desc != null && desc) {
                direction = Sort.Direction.DESC;
            }

            Sort sort = Sort.by(direction, orderByField);

            return PageRequest.of(pageNumber, pageSize, sort);
        } else {
            return getDefaultPageRequest(pageNumber, pageSize);
        }
    }

    public void checkField(List<String> allowedFields, String field) {
        if (!allowedFields.contains(field)) {
            throw new RestException(HttpStatus.FORBIDDEN, "<" + field + "> not allowed");
        }
    }
        public PageRequest getDefaultPageRequest(Integer pageNumber, Integer pageSize) {
            return PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "creationDate"));
        }
}

package ir.bigz.ms.searchfilter.common.utility;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageableUtility {

    public Pageable createPageable(Integer page, Integer size){
        if(page == null) return null;
        return createPageable(page, size, Sort.by("id").descending());
    }

    private Pageable createPageable(Integer page, Integer size, Sort sort){
        if(page == null) return null;
        return PageRequest.of(
                page - 1,
                size,
                sort
        );
    }
}

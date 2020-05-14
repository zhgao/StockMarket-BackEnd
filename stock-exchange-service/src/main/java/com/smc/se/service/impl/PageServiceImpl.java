package com.smc.se.service.impl;

import com.smc.se.dto.PageDto;
import com.smc.se.service.Converter;
import com.smc.se.service.IPageService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PageServiceImpl implements IPageService {
    @Override
    public <S, T> PageDto<T> convertToPageDto(Page<S> page, Converter<S, T> converter) {
        List<T> list = page.getContent().stream()
                .map(converter::convert)
                .collect(Collectors.toList());

        PageDto<T> pageDto = new PageDto<>();
        pageDto.setList(list);
        pageDto.setTotal(page.getTotalElements());
        return pageDto;
    }
}

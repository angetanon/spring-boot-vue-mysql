package com.tanon.abedjinan.springbootvue.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.tanon.abedjinan.springbootvue.model.Todo;
import com.tanon.abedjinan.springbootvue.utils.LinkDTO;
import com.tanon.abedjinan.springbootvue.utils.TodoDTO;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {
	@Mapping(source = "id", target = "value")
	TodoDTO toDTO(Todo todo);

	@InheritInverseConfiguration
	@Mapping(target = "id", ignore = true)
	void fromDTO(TodoDTO dto, @MappingTarget Todo todo);
	
	 default LinkDTO todoToLinkDTO(Todo todo) {
	        return todo.asLink();
	    }

}

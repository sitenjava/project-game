package com.game.data.dto;

public class CategoryDto extends BaseDto<CategoryDto>
{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.warrr.zipflex.api.house.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealInfoResponseDto {
	private long id;
	private long houseInfoId;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private int dealAmount;
	private int monthlyRent;
	private float exclusiveArea;
	private int floor;
}
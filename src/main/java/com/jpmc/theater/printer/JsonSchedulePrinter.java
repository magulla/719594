package com.jpmc.theater.printer;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jpmc.theater.Schedule;
import com.jpmc.theater.utils.TextUtils;

public class JsonSchedulePrinter implements SchedulePrinter{
	@Override
    public void printSchedule(Schedule schedule) {
		Map<String, Object> map = new HashMap();
		map.put("currentDate", schedule.currentDate().toString());
		List<Map<String, Object>> items = schedule.getShowings().stream().map((s)->{
			Map<String,Object> record = new LinkedHashMap();
			record.put("sequenceOfTheDay", s.getSequenceOfTheDay());
			record.put("startTime", s.getStartTime().toString());
			record.put("title", s.getMovie().getTitle());
			record.put("running", s.getMovie().getRunningTime().toMinutes());
			record.put("price", s.getMovieFee());
			return record;}).collect(Collectors.toList());
		Collections.reverse(items);
		map.put("showings", items);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String asJson = gson.toJson(map);
		System.out.println(asJson);

	}
}

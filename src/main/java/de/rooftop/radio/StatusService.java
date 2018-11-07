package de.rooftop.radio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class StatusService {

	List<Exception> exceptions = new ArrayList<>();

	public List<Exception> getExceptions() {

		return exceptions;
	}

	public void logException(Exception e) {

		exceptions.add(e);
	}
}

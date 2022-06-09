package com.example.danque.common.cache;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CachedData<T> {

	private T payload;

	public CachedData() {}

	public CachedData(T payload) {
		this.payload = payload;
	}
}

package com.example.webmvc.actuator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.endpoint.Endpoint;
import org.springframework.boot.endpoint.ReadOperation;
import org.springframework.boot.endpoint.Selector;
import org.springframework.boot.endpoint.WriteOperation;
import org.springframework.stereotype.Component;

/**
 * A dummy foo endpoint.
 *
 * @author Stephane Nicoll
 */
@Endpoint(id = "foo")
@Component
public class FooEndpoint {

	private final Map<String, Foo> all = new HashMap<>();

	FooEndpoint() {
		this.all.put("one", new Foo("one"));
		this.all.put("two", new Foo("two"));
	}

	@ReadOperation
	public List<Foo> getAll() {
		return new ArrayList<>(this.all.values());
	}

	@ReadOperation
	public Foo getFoo(@Selector String name) {
		return this.all.get(name);
	}

	@WriteOperation
	public void updateFoo(@Selector String name, String value) {
		this.all.put(name, new Foo(value));
	}

	public static class Foo {
		private String name;

		Foo(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}

package br.abs.apiTest;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ApiTest {
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
	}
	
	@Test
	public void deveRetornarTarefas() {
//		RestAssured.given()
//				.log().all()
//			.when()
//				.get("http://localhost:8001/tasks-backend/todo")
//			.then()
//				.log().all()
//			;
		RestAssured.given()
			.when()
				.get("/todo")
			.then()
				.statusCode(200)
		;
	}
	
	@Test
	public void deveAdicionarTarefa() {
//		RestAssured.given()
//				.log().all()
//			.when()
//				.get("http://localhost:8001/tasks-backend/todo")
//			.then()
//				.log().all()
//			;
		RestAssured.given()
			.contentType(ContentType.JSON)
			.body("{\"task\":\"teste via api\",\"dueDate\":\"2021-12-20\"}")
		.when()
			.post("/todo")
		.then()
			.statusCode(201)
		;
	}
	
	@Test
	public void naoDeveAdicionarTarefaInvalida() {
//		RestAssured.given()
//				.log().all()
//			.when()
//				.get("http://localhost:8001/tasks-backend/todo")
//			.then()
//				.log().all()
//			;
		RestAssured.given()
			.contentType(ContentType.JSON)
			.body("{\"task\":\"teste via api\",\"dueDate\":\"2019-12-20\"}")
		.when()
			.post("/todo")
		.then()
			.statusCode(400)
			.body("message", CoreMatchers.is("Due date must not be in past"))
		;
	}

}
// dessa forma o eclipse ajuda quando colar
//{"task":"teste via api","dueDate":"2021-12-20"}





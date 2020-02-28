module cardDataCleaner {
	requires com.google.gson;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;
	exports JsonStuff to com.google.gson;
	opens JsonStuff to com.google.gson;
}
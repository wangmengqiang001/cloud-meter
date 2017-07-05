package org.cloudmeter.model;

import java.io.IOException;

import org.apache.jmeter.testelement.TestElement;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.HashTreeTraverser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class HashTreeSerializer extends JsonSerializer<HashTree>  {
	
	private static final Logger log = LoggerFactory.getLogger(HashTreeSerializer.class);

	@Override
	public void serialize(HashTree tree, JsonGenerator generator, SerializerProvider serializer){
		
		log.debug("serializing a hash tree.");
		
		try {
			generator.writeStartArray();
			tree.traverse(new JSonTraverser(generator));
			generator.writeEndArray();
		} catch (IOException e) {
			log.error("Cannot begin or end testplan serialization. Exception type: {}, message {}.",
				 e.getClass().getName(), e.getMessage());
		}

		
	}
	
	class JSonTraverser implements HashTreeTraverser {

		private JsonGenerator jsonGen;
		
		JSonTraverser(JsonGenerator arg1){
			this.jsonGen = arg1;
		}
		
		@Override
		public void addNode(Object node, HashTree subTree) {
			log.debug("Adding node {} to tree.", node.getClass().getSimpleName());			


			
			try {
//				TestElement ele =  (TestElement) node;
//				if(isNodeWorkBench((TestElement) node)){
//					jsonGen.writeEndObject();	//close the testplan object
//					jsonGen.writeObjectFieldStart("workbench");	
//				}
				jsonGen.writeStartObject();
				jsonGen.writeObjectField("testelement", node);
				jsonGen.writeArrayFieldStart("hashTree");
				
				
				
				
				
			} catch (IOException e) {
				
				log.error("Cannot add node {}. Exception type: {}, message {}.",
						node.getClass().getSimpleName(), e.getClass().getName(), e.getMessage());
			}
//			  catch(ClassCastException e) {
//				log.error("Can't cast node object to test element. Message {}.", e.getMessage()));
//			}
				

		}

		@Override
		public void subtractNode() {
			log.debug("Subtracting node");
			
			try {
				this.jsonGen.writeEndArray();
				this.jsonGen.writeEndObject();
			} catch (IOException e) {
				log.error("Cannot finish node. Exception type: {}, message {},", 
						e.getClass().getName(), e.getMessage());
			}
			
		}

		@Override
		public void processPath() {
			log.debug("processing path. ");
			
//			try {
//				this.jsonGen.writeEndArray();
//			} catch (IOException e) {
//				log.error("Cannot finish node. Exception type: {}, message {},", 
//						e.getClass().getName(), e.getMessage());
//			}
		}
		
		
//		private boolean isNodeWorkBench(TestElement ele) {
//			return ele.getPropertyAsString("TestElement.name").equals("WorkBench");
//		}

		
		
	}
	

}

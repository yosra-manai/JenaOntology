/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jenaapi;



import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;



class OpenOWL {

     static  String  s;

      // Open a connection to MonFichierOwl.OWL

     static  OntModel OpenConnectOWL(){
        
    OntModel mode = null;
    mode = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_RULE_INF );
    java.io.InputStream in = FileManager.get().open("C:\\Users\\user\\Documents\\NetBeansProjects\\JenaAPIoNTOLOGY\\src\\jenaapi\\obesitymanagement.owl");  //MyFile

    //test
    if (in == null) {
        throw new IllegalArgumentException("Pas de base de connaissance");  // there i no file to connect
    }
        return  (OntModel) mode.read(in, "");
    }


    // jena.query.ResultSet  return

     static  ResultSet ExecSparQl(String Query){
         
                Query query = QueryFactory.create(Query);

                QueryExecution qe = QueryExecutionFactory.create(query, OpenConnectOWL());
                ResultSet results = qe.execSelect();

                return results;  // Retrun jena.query.ResultSet 
         
     }

     // String return (convert jena.query.ResultSet  to String)

      static  String ExecSparQlString(String Query){
        try {
                Query query = QueryFactory.create(Query);

                  QueryExecution qe = QueryExecutionFactory.create(query, OpenConnectOWL());

                  ResultSet results = qe.execSelect();

                  // Test
                  if(results.hasNext()){

                  	// if iS good 

                    ByteArrayOutputStream go = new ByteArrayOutputStream ();
                    ResultSetFormatter.out((OutputStream)go ,results, query);
                  //  String s = go.toString();
                       s = new String(go.toByteArray(), "UTF-8");
                   }
                   // not okay
                  else{

                      s = "rien";
                  }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(OpenOWL.class.getName()).log(Level.SEVERE, null, ex);
        }
         return s;   // return  jena.query.ResultSet  as string 
      }
    
}


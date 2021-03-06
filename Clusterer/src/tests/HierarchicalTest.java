package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import input.Dataset;
import input.FeatureVector;
import input.InputReader;

import org.junit.Before;
import org.junit.Test;

import algorithms.HierarchicalClustering;
import algorithms.KMeans;
import distance.EuclideanDistance;
import distance.linkage.SingleLinkage;

/**
 * @author Markus
 *
 */
public class HierarchicalTest {
	
	private Dataset testset;
	@Before
	public void setUp(){
			InputReader inputReader = new InputReader();
			this.testset =inputReader.readFromfile("C:\\Users\\Markus\\Documents\\Masterarbeit\\Workspace\\Clusterer\\src\\90.valid");
			this.testset.reset();
	}
	
	
	
	@Test
	public void testHierarchicalClustering(){
		for (FeatureVector featureVector : testset) {
			assertEquals(FeatureVector.UNCLASSIFIED ,featureVector.getCalculatedClusternumber() );
		}
		HierarchicalClustering htClusterer = new HierarchicalClustering(new SingleLinkage(new EuclideanDistance()));
		htClusterer.setLimit(745);
		htClusterer.doClustering(testset);
		
		for (FeatureVector featureVector : testset) {
			 assertFalse(FeatureVector.UNCLASSIFIED == featureVector.getCalculatedClusternumber());
		}
		System.out.print(testset.toString());
	}
}
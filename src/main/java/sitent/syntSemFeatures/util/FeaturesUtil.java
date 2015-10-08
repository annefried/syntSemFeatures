package sitent.syntSemFeatures.util;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import sitent.types.ClassificationAnnotation;
import sitent.types.SEFeature;

public class FeaturesUtil {

	public static void addFeature(String name, String value, JCas jcas, ClassificationAnnotation segment) {

		SEFeature feat = new SEFeature(jcas);
		feat.setName(name);
		feat.setValue(value);
		feat.addToIndexes();
		segment.setFeatures(SitEntUimaUtils.addToFSList(segment.getFeatures(), feat, jcas));
	}

	/**
	 * A convenience method for printing the features and values of a
	 * ClassificationAnnotation to the console. (Just for debugging.)
	 * 
	 * @param classAnnot
	 *            ClassificationAnnotation instance, features and values are
	 *            printed for inspection.
	 */
	public static void printFeatures(ClassificationAnnotation classAnnot) {

		for (Annotation annot : SitEntUimaUtils.getList(classAnnot.getFeatures())) {
			SEFeature feat = (SEFeature) annot;
			System.out.println("\t" + feat.getName() + "\t" + feat.getValue());
		}
	}

}

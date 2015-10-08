
/* First created by JCasGen Fri Jul 10 16:47:03 CEST 2015 */
package sitent.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** adds tense and voice information
 * Updated by JCasGen Wed Sep 09 15:50:35 CEST 2015
 * @generated */
public class VerbFeatures_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (VerbFeatures_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = VerbFeatures_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new VerbFeatures(addr, VerbFeatures_Type.this);
  			   VerbFeatures_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new VerbFeatures(addr, VerbFeatures_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = VerbFeatures.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("sitent.types.VerbFeatures");
 
  /** @generated */
  final Feature casFeat_tense;
  /** @generated */
  final int     casFeatCode_tense;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getTense(int addr) {
        if (featOkTst && casFeat_tense == null)
      jcas.throwFeatMissing("tense", "sitent.types.VerbFeatures");
    return ll_cas.ll_getStringValue(addr, casFeatCode_tense);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTense(int addr, String v) {
        if (featOkTst && casFeat_tense == null)
      jcas.throwFeatMissing("tense", "sitent.types.VerbFeatures");
    ll_cas.ll_setStringValue(addr, casFeatCode_tense, v);}
    
  
 
  /** @generated */
  final Feature casFeat_voice;
  /** @generated */
  final int     casFeatCode_voice;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getVoice(int addr) {
        if (featOkTst && casFeat_voice == null)
      jcas.throwFeatMissing("voice", "sitent.types.VerbFeatures");
    return ll_cas.ll_getStringValue(addr, casFeatCode_voice);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setVoice(int addr, String v) {
        if (featOkTst && casFeat_voice == null)
      jcas.throwFeatMissing("voice", "sitent.types.VerbFeatures");
    ll_cas.ll_setStringValue(addr, casFeatCode_voice, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public VerbFeatures_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_tense = jcas.getRequiredFeatureDE(casType, "tense", "uima.cas.String", featOkTst);
    casFeatCode_tense  = (null == casFeat_tense) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tense).getCode();

 
    casFeat_voice = jcas.getRequiredFeatureDE(casType, "voice", "uima.cas.String", featOkTst);
    casFeatCode_voice  = (null == casFeat_voice) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_voice).getCode();

  }
}



    
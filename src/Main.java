import file.RecourceControl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EClassImpl;

public class Main {

    public static void main(String[] args) {
        EPackage ePackage = null;
        try {
            ePackage = RecourceControl.SINGLETON.getEcore("resource/ecores/basicfamily.ecore");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.exit(0);
        }

        assert ePackage != null;

        System.out.println("all non-abstract classes in ecore metamodel:\n");
        for (EClassifier classifier : ePackage.getEClassifiers()) {
            // 仅考虑类，筛选EClassImpl实现
            if (classifier instanceof EClassImpl && !((EClassImpl) classifier).isAbstract()) {
                System.out.print(classifier.getName() + " : ");
                // 判断是否有父类
                if (!((EClassImpl) classifier).getESuperTypes().isEmpty()) {
                    System.out.print("extends ");
                    for (EClass superClass: ((EClassImpl) classifier).getESuperTypes()) {
                        System.out.println(superClass.getName());
                    }
                } else {
                    System.out.println();
                }
                // 筛选对其他类的引用，得出类间关系

                for (EReference reference: ((EClassImpl) classifier).getEAllReferences()) {
                    System.out.println("\t\"" + reference.getName() + "\"" +
                            (((EReference) reference).isContainment() ? "(is containment): " : ": ")
                            + reference.eContainer());
                }
//                for (EStructuralFeature feature: ((EClassImpl)classifier).getEAllStructuralFeatures()) {
//                    if (feature instanceof EReference) {
//                        System.out.println("\t\"" + feature.getName() + "\"" +
//                                        (((EReference) feature).isContainment() ? "(is containment): " : ": ")
//                                        + feature.eContainer());
//                    }
//                }
            }
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

/**
 *
 * @author felipe
 */
public interface AnalyzerListener {
    public void analyzingStarted();
    public void analyzingFinished();
    public void newFileFound();
}

/*
 * User: DJoiner
 * Date: Oct 8, 2002
 * Time: 11:10:09 AM
 */
package net.sourceforge.jwebunit.fit;

import fit.Fixture;

import java.io.File;

public abstract class FitResultWriter {
    private File output;

    FitResultWriter(File output) {
        this.output = output;
    }

    public abstract Fixture.Counts getCounts();

    public abstract String getLinkString();
    public abstract String getDisplayName();
    public abstract void write();


    public boolean didFail() {
        return getWrong() > 0 || getExceptions() > 0;
    }

    public int getRight() {
        return getCounts().right;
    }

    public int getWrong() {
        return getCounts().wrong;
    }

    public int getIgnores() {
        return getCounts().ignores;
    }

    public int getExceptions() {
        return getCounts().exceptions;
    }

    public File getOutput() {
        return output;
    }
}
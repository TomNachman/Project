package algorithms.search;

import algorithms.mazeGenerators.Position;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public abstract class AState {
    private int val;
    private AState cameFrom;
    private Position position;

    public AState(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return position.toString();
    }
}


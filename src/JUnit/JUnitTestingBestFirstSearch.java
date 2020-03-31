import static org.junit.jupiter.api.Assertions.*;

import algorithms.search.*;
import algorithms.mazeGenerators.*;
import java.util.ArrayList;

class JUnitTestingBestFirstSearch {

    @org.junit.jupiter.api.Test
    public void NullToBestFS() {
        ISearchingAlgorithm BestFS = new BestFirstSearch();
        BestFS.solve(null);
    }
}
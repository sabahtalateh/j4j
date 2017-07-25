package com.sabahtalateh.j4j.collections_lite.organizations;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * DivisionCollectionTest.
 */
public class DivisionCollectionTest {
    @Test
    public void whenAddSingleNodeItPersistsInCollection() throws Exception {
        DivisionCollection divisionCollection = new DivisionCollection();
        divisionCollection.add("K1");

        assertThat(divisionCollection.getDivisions().get(0), is(new Division("K1")));
    }

    @Test
    public void whenAddHierarchyNodesItPersistsInCollection() throws Exception {
        DivisionCollection divisionCollection = new DivisionCollection();
        divisionCollection.add("K1\\SK1\\SSK2");
        divisionCollection.add("K1\\SK2");

        Division root = new Division("K1");
        Division sk1 = new Division("SK1");
        Division sk2 = new Division("SK2");
        Division ssk2 = new Division("SSK2");

        assertThat(divisionCollection.getDivisions().get(0), is(root));

        assertThat(
                divisionCollection.getDivisions().get(0).getSubdivisions().get(0),
                is(sk1)
        );
        assertThat(
                divisionCollection.getDivisions().get(0).getSubdivisions().get(1),
                is(sk2)
        );
        assertThat(
                divisionCollection.getDivisions().get(0).getSubdivisions().get(0).getSubdivisions().get(0),
                is(ssk2)
        );
    }

}
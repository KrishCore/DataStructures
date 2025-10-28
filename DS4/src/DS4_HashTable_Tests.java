//
//import org.junit.Test;
//
//import java.util.*;
//import java.lang.reflect.Field;
//
//import static org.junit.Assert.*;
//
//public class DS4_HashTable_Tests {
//
//    // =========================
//    // Reflection helpers for tests 36–45
//    // =========================
//    @SuppressWarnings("unchecked")
//    private static ArrayList<ArrayList<?>> getRawTable(Object ht) {
//        try {
//            Field f = ht.getClass().getDeclaredField("table");
//            f.setAccessible(true);
//            return (ArrayList<ArrayList<?>>) f.get(ht);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /** Snapshot buckets into List<List<String>>:
//     *  - non-null entries use entry.toString()  (should be "key:value")
//     *  - tombstones (nulls) as "null"
//     */
//    private static java.util.List<java.util.List<String>> snapshotTable(Object ht) {
//        ArrayList<ArrayList<?>> raw = getRawTable(ht);
//        java.util.List<java.util.List<String>> snap = new ArrayList<>();
//        for (ArrayList<?> bucket : raw) {
//            java.util.List<String> b = new ArrayList<>();
//            for (Object e : bucket) {
//                if (e == null) {
//                    b.add("null");
//                } else {
//                    b.add(e.toString());   // expects DS4_Entry.toString() -> "key:value"
//                }
//            }
//            snap.add(b);
//        }
//        return snap;
//    }
//
//    /** Count non-null entries across all buckets. */
//    private static int countEntries(Object ht) {
//        int count = 0;
//        for (ArrayList<?> bucket : getRawTable(ht)) {
//            for (Object e : bucket) if (e != null) count++;
//        }
//        return count;
//    }
//
//    /** Count tombstone nulls across all buckets. */
//    private static int countTombstonesFromTable(Object ht) {
//        int count = 0;
//        for (ArrayList<?> bucket : getRawTable(ht)) {
//            for (Object e : bucket) if (e == null) count++;
//        }
//        return count;
//    }
//
//    // =========================
//    // Original tests 1–25 (from your prompt)
//    // =========================
//
//    // 1 Contains Key
//    @Test
//    public void testInsertAndContains() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(2, 6, 4);
//        assertFalse(ht.contains(1));
//        ht.insert(1, "A");
//        ht.insert(2, "B");
//        assertTrue(ht.contains(1));
//        assertTrue(ht.contains(2));
//    }
//
//    // 2
//    @Test
//    public void testReplaceReturnsOldValue() {
//        DS4_HashTable<String, String> ht = new DS4_HashTable<>(2, 6, 4);
//        assertNull(ht.insert("k", "one"));
//        assertEquals("one", ht.insert("k", "two"));
//        assertTrue(ht.contains("k"));
//        assertEquals(1, ht.size());
//        assertEquals(0, ht.tombstones());
//    }
//
//    // 3
//    @Test
//    public void testRemoveCreatesTombstone() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(2, 6, 4);
//        ht.insert(10, "X");
//        ht.insert(20, "Y");
//        assertEquals("X", ht.remove(10));
//        assertFalse(ht.contains(10));
//        assertTrue(ht.contains(20));
//        assertEquals(1, ht.size());
//        assertEquals(1, ht.tombstones());
//    }
//
//    // 4
//    @Test
//    public void testEarlyStopRule() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(3, 10, 8);
//        ht.insert(9999, "X");
//        assertFalse(ht.contains(123));
//        assertNull(ht.remove(123));
//    }
//
//    // 5
//    @Test
//    public void testLinearRehashingAcrossBuckets() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(1, 4, 4);
//        int base = 4000;
//        int k1 = base;
//        int k2 = base + 4;
//        int k3 = base + 8;
//        ht.insert(k1, "A");
//        ht.insert(k2, "B");
//        ht.insert(k3, "C");
//        assertTrue(ht.contains(k1));
//        assertTrue(ht.contains(k2));
//        assertTrue(ht.contains(k3));
//        assertEquals(3, ht.size());
//    }
//
//    // 6
//    @Test
//    public void testIteratorSnapshot() {
//        DS4_HashTable<String, Integer> ht = new DS4_HashTable<>(2, 8, 4);
//        ht.insert("a", 1);
//        ht.insert("b", 2);
//        ht.insert("c", 3);
//
//        Iterator<String> it = ht.iterator();
//        // mutate after obtaining iterator
//        ht.insert("d", 4);
//
//        HashSet<String> keys = new HashSet<>();
//        it.forEachRemaining(keys::add);
//
//        assertEquals(Set.of("a","b","c"), keys);
//        assertTrue(ht.contains("d"));
//    }
//
//    // 7
//    @Test
//    public void testRebuildOnLoadFactorExceeded() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(1, 2, 2);
//        ht.insert(1, 10);
//        ht.insert(2, 20);
//        ht.insert(3, 30); // should trigger rebuild
//        assertTrue(ht.contains(1));
//        assertTrue(ht.contains(2));
//        assertTrue(ht.contains(3));
//        assertEquals(3, ht.size());
//        assertEquals(4, ht.tableSize());
//        ht.insert(4, 40);
//        ht.insert(5, 50);
//        ht.insert(6, 60);
//        assertTrue(ht.contains(6));
//    }
//
//    // 8
//    @Test
//    public void testSizeExcludesTombstones() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(2, 8, 4);
//        ht.insert(1, "A");
//        ht.insert(2, "B");
//        ht.insert(3, "C");
//        assertEquals(3, ht.size());
//
//        ht.remove(2);
//        assertEquals(2, ht.size());
//        assertEquals(1, ht.tombstones());
//    }
//
//    // 9
//    @Test
//    public void testClear() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(2, 8, 4);
//        ht.insert(1, "A");
//        ht.insert(2, "B");
//        ht.remove(1);
//        assertEquals(1, ht.tombstones());
//        ht.clear();
//        assertEquals(0, ht.size());
//        assertEquals(0, ht.tombstones());
//        assertFalse(ht.contains(1));
//        assertFalse(ht.contains(2));
//    }
//
//    // 10
//    @Test
//    public void testRemoveNonexistentEarlyStop() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(2, 10, 8);
//        ht.insert(100, "X");
//        assertNull(ht.remove(200)); // bucket for 200 not at capacity → early stop
//    }
//
//    // 11
//    @Test
//    public void testInsertDoesNotFillTombstoneHole() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(1, 4, 4);
//        int k1 = 4;     // hashes to bucket 0 (depends on tableSize=4)
//        int k2 = 8;     // same bucket
//        ht.insert(k1, "A");              // bucket 0: [A]
//        assertEquals("A", ht.remove(k1)); // bucket 0: [null] (tombstone)
//        // capacity is 1, so bucket.size()==1 (==capacity); inserting k2 goes to next bucket
//        ht.insert(k2, "B");
//        assertTrue(ht.contains(k2));
//        assertEquals(1, ht.tombstones());
//    }
//
//    // 12
//    @Test
//    public void testReplaceDoesNotChangeCounts() {
//        DS4_HashTable<String, Integer> ht = new DS4_HashTable<>(2, 8, 4);
//        ht.insert("x", 1);
//        assertEquals(1, ht.size());
//        assertEquals(0, ht.tombstones());
//        ht.insert("x", 2);
//        assertEquals(1, ht.size());
//        assertEquals(0, ht.tombstones());
//    }
//
//    // 13
//    @Test
//    public void testMultipleTombstonesThenRebuildClearsTombstones() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(1, 4, 4);
//        ht.insert(1, 1);
//        ht.insert(2, 2);
//        ht.insert(3, 3);
//        // Remove two to create tombstones
//        ht.remove(1);
//        ht.remove(2);
//        assertEquals(2, ht.tombstones());
//        // Next insert should push (size + tombstones) to loadFactor and trigger cleanup + rebuild
//        ht.insert(4, 4);
//        // After rebuild tombstones should be 0
//        assertEquals(0, ht.tombstones());
//    }
//
//    // 14
//    @Test
//    public void testIteratorExcludesTombstones() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(2, 8, 4);
//        ht.insert(1, "A");
//        ht.insert(2, "B");
//        ht.remove(1);
//
//        assertEquals(1, ht.size());
//    }
//
//    // 15
//    @Test
//    public void testWrapAroundProbing() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(1, 3, 3); // small table
//        int a = 3;  // mod 0
//        int b = 6;  // mod 0
//        int c = 9;  // mod 0
//
//        ht.insert(a, "A"); // bucket 0
//        ht.insert(b, "B"); // bucket 1
//        ht.insert(c, "C"); // bucket 2 (wrap after next)
//        //rebuild
//        assertTrue(ht.contains(a));
//        assertTrue(ht.contains(b));
//        assertTrue(ht.contains(c));
//        assertEquals(3, ht.size());
//    }
//
//    // 17
//    @Test
//    public void testRebuildPreservesAllMappings() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(1, 2, 2);
//        Map<Integer,String> map = new HashMap<>();
//        for (int i = 0; i < 20; i++) {
//            map.put(i, "V"+i);
//            ht.insert(i, "V"+i);
//        }
//        for (int i = 0; i < 20; i++) {
//            assertTrue(ht.contains(i));
//        }
//        for (int i = 0; i < 20; i++) {
//            int k = i;
//            assertEquals(map.get(k), ht.remove(k));
//        }
//        assertEquals(0, ht.size());
//        assertEquals(20, ht.tombstones());
//        assertEquals(32, ht.tableSize());
//    }
//
//    // 18
//    @Test
//    public void testRemoveThenContainsChainStillFindsLaterKey() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(1, 4, 4);
//        int b = 8, c = 12, d = 16; // all mod 0 with tableSize 4
//        ht.insert(b, "B"); // bucket 0
//        ht.insert(c, "C"); // bucket 1
//        ht.insert(d, "D"); // bucket 2
//        assertEquals("C", ht.remove(c));
//        assertTrue(ht.contains(d));
//    }
//
//    // 19
//    @Test
//    public void testRemoveNonExistentWithFullFirstBucketContinues() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(1, 4, 4);
//        int a = 4, b = 8; // same start bucket
//        ht.insert(a, "A"); // bucket full at capacity 1
//        assertNull(ht.remove(12)); // not found; first bucket full so we probe next bucket (which is empty)
//        assertTrue(ht.contains(a));
//    }
//
//    // 20
//    @Test
//    public void testInsertReturnNullWhenNoReplacement() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(2, 4, 4);
//        assertNull(ht.insert(1, 100));
//        assertNull(ht.insert(2, 200));
//        assertEquals(2, ht.size());
//        assertEquals(0, ht.tombstones());
//        assertEquals(4, ht.tableSize());
//    }
//
//    // 21
//    @Test
//    public void testMultipleReplacements() {
//        DS4_HashTable<String, String> ht = new DS4_HashTable<>(2, 8, 4);
//        assertNull(ht.insert("k", "v1"));
//        assertEquals("v1", ht.insert("k", "v2"));
//        assertEquals("v2", ht.insert("k", "v3"));
//        assertEquals(1, ht.size());
//        assertTrue(ht.contains("k"));
//        assertEquals(0, ht.tombstones());
//    }
//
//    // 22 — (FIXED) Load-factor reachable after tombstones accumulate
//    @Test
//    public void testTombstonesAccumulateThenResetAfterRebuild() {
//        // Capacity = tableSize * bucketCapacity = 6, loadFactor = 6 (reachable)
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(1, 6, 6);
//        // Insert 4 → size=4
//        for (int i = 0; i < 4; i++) ht.insert(i, i);
//        // Remove 2 → size=2, tombs=2, total=4
//        ht.remove(0);
//        ht.remove(2);
//        assertEquals(2, ht.tombstones());
//        // Insert until total hits loadFactor (6): need 2 more total
//        ht.insert(100, 100); // total=5
//        ht.insert(101, 101); // total=6 → cleanup + rebuild
//        assertEquals(0, ht.tombstones()); // tombstones cleared by rebuild
//        assertTrue(ht.contains(1));
//        assertTrue(ht.contains(3));
//        assertTrue(ht.contains(100));
//        assertTrue(ht.contains(101));
//        assertEquals(12, ht.tableSize());
//        assertEquals(4, ht.size());
//    }
//
//    // 23
//    @Test
//    public void testIteratorContainsAllKeysNoDuplicates() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(2, 32, 16);
//        Set<Integer> inserted = new HashSet<>();
//        for (int i = 0; i < 40; i++) {
//            inserted.add(i);
//            ht.insert(i, i);
//        }
//        List<Integer> keys = new ArrayList<>();
//
//        Iterator<Integer> it = ht.iterator();
//        while (it.hasNext()) keys.add(it.next());
//        assertEquals(inserted.size(), new HashSet<>(keys).size());
//        assertTrue(new HashSet<>(keys).containsAll(inserted));
//        assertEquals(40,ht.size());
//        assertEquals(32,ht.tableSize());
//    }
//
//    // 24
//    @Test
//    public void testClearThenReuse() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(2, 8, 4);
//        ht.insert(1, "A");
//        ht.insert(2, "B");
//        ht.clear();
//        ht.insert(3, "C");
//        assertFalse(ht.contains(1));
//        assertTrue(ht.contains(3));
//        assertEquals(1, ht.size());
//    }
//
//
//    // =========================
//    // Rebuild-focused tests 26–35 (use tableSize(); capture before/after)
//    // =========================
//
//    // 26 — Load-factor on exact threshold
//    @Test
//    public void testRebuildOnExactThreshold() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(1, 3, 4);
//        ht.insert(0, 0);
//        ht.insert(1, 1);
//
//        int preSize = ht.size();
//        int preTombs = ht.tombstones();
//        int preTableSize = ht.tableSize();
//
//        ht.insert(2, 2); // (size+tombs) == 3 → rebuild
//
//        int postSize = ht.size();
//        int postTombs = ht.tombstones();
//        int postTableSize = ht.tableSize();
//
//        assertEquals(preSize + 1, postSize);
//        assertEquals(0, postTombs);
//        assertEquals(preTableSize * 2, postTableSize);
//    }
//
//    // 27 — Load-factor with tombstone cleanup
//    @Test
//    public void testRebuildWithTombstoneCleanup() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(1, 4, 4);
//        ht.insert(0, 0);
//        ht.insert(1, 1);
//        ht.insert(2, 2);
//        ht.remove(1);              // size=2, tombs=1, total=3 < 4
//
//        int preSize = ht.size();
//        int preTombs = ht.tombstones();
//        int preTableSize = ht.tableSize();
//
//        ht.insert(3, 3); // total hits 4 → cleanup + rebuild
//
//        int postSize = ht.size();
//        int postTombs = ht.tombstones();
//        int postTableSize = ht.tableSize();
//
//        assertEquals(preSize + 1, postSize);
//        assertTrue(preTombs > 0);
//        assertEquals(0, postTombs);
//        assertEquals(preTableSize * 2, postTableSize);
//    }
//
//    // 28 — Saturation (on purpose: very high loadFactor)
//    @Test
//    public void testForceRebuildAndReinsertOnSaturation() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(1, 2, 2);
//        ht.insert(0, 0); // bucket 0
//
//
//        int preTombs = ht.tombstones();
//        int preTableSize = ht.tableSize();
//
//        ht.insert(1, 1); // bucket 1
//        ht.insert(2, 2); // no room anywhere → forceRebuildAndReinsert
//
//        int postSize = ht.size();
//        int postTombs = ht.tombstones();
//        int postTableSize = ht.tableSize();
//
//        assertEquals(3, postSize);
//        assertEquals(preTombs, postTombs);
//        assertEquals(preTableSize * 2, postTableSize);
//    }
//
//    // 29 — (FIXED) Saturation with wrap-around + tombstone in path
//    @Test
//    public void testRebuildAfterWrapAroundWithTombstone() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(1, 3, 3); // high LF → saturation path
//        // Fill all three buckets
//        ht.insert(0, 0); // idx 0
//        ht.insert(1, 1); // idx 1
//        //rebuild
//        ht.insert(2, 2);
//        ht.remove(1);
//
//        ht.insert(4, 4); // must force rebuild due to saturation (each bucket size==capacity)
//        //rebuild
//
//        assertEquals(3,ht.size());
//        assertEquals(1, ht.tombstones()); // cleared by rebuild
//        assertEquals(6, ht.tableSize());
//    }
//
//    // 30 — (FIXED) Load-factor with multiple tombstones across buckets
//    @Test
//    public void testRebuildWithMultipleTombstones() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(1, 4, 4); // LF 4 reachable (capacity 4)
//        ht.insert(0, 0);
//        ht.insert(1, 1);
//        ht.insert(2, 2);
//        ht.insert(3, 3);  // size=4 → rebuild happens here already
//
//        // After rebuild, no tombstones should exist
//        assertEquals(0, ht.tombstones());
//        assertEquals(8,ht.tableSize()); // doubled
//
//        // Now verify further inserts still work post-rebuild
//        ht.insert(4, 4);
//        assertTrue(ht.contains(4));
//    }
//
//
//
//    // 32 — (FIXED) Load-factor with bucketCapacity > 1; rebuild on 5th insert (no saturation)
//    @Test
//    public void testRebuildWithCapacityTwoBucketsFullyPacked() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(2, 5, 3); // capacity=6, LF=5 reachable
//        // We’ll place 4, then the 5th insert trips LF (not saturation)
//        ht.insert(0, 0);  // bucket 0
//        ht.insert(2, 2);  // bucket 0 (room)
//        ht.insert(1, 1);  // bucket 1
//        ht.insert(3, 3);  // bucket 1 (room)
//
//        int preSize = ht.size();
//        int preTombs = ht.tombstones();
//        int preTableSize = ht.tableSize();
//
//        ht.insert(4, 4);  // 5th → LF reached → rebuild
//
//        int postSize = ht.size();
//        int postTombs = ht.tombstones();
//        int postTableSize = ht.tableSize();
//
//        assertEquals(preSize + 1, postSize);
//        assertEquals(0, postTombs);
//        assertEquals(preTableSize * 2, postTableSize);
//    }
//
//    // 33 — Load-factor after long probe chain
//    @Test
//    public void testRebuildAfterLongProbeChain() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(1, 4, 4);
//        // Force a chain: all keys ≡ 0 (mod 4)
//        int base = 4000; // 4000 % 4 == 0
//        ht.insert(base, 1);        // idx 0
//        ht.insert(base + 4, 2);    // idx 1
//        ht.insert(base + 8, 3);    // idx 2
//
//        int preSize = ht.size();    // 3
//        int preTombs = ht.tombstones();
//        int preTableSize = ht.tableSize();
//
//        ht.insert(base + 12, 4); // (size+tombs)==4 → rebuild
//
//        int postSize = ht.size();
//        int postTombs = ht.tombstones();
//        int postTableSize = ht.tableSize();
//
//        assertEquals(preSize + 1, postSize);
//        assertEquals(0, postTombs);
//        assertEquals(preTableSize * 2, postTableSize);
//    }
//
//    // 34 — Saturation with all buckets tombstoned
//    @Test
//    public void testForceRebuildWithAllBucketsTombstoned() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(1, 2, 2);
//        // Fill then tombstone both buckets
//        ht.insert(0, 0);
//        ht.insert(1, 1);
//        //rebuild
//        ht.remove(0);
//        ht.remove(1);
//        assertEquals(0, ht.size());
//        assertEquals(2, ht.tombstones()); // buckets still at size==capacity (nulls)
//
//
//        ht.insert(2, 2);
//
//        int postSize = ht.size();
//        int postTombs = ht.tombstones();
//        int postTableSize = ht.tableSize();
//
//        assertEquals(1, postSize);  // 1
//        assertEquals(2, postTombs);           // cleared by rebuild
//        assertEquals(4, postTableSize);
//    }
//
//    // 35 — Load-factor then verify next insert uses new sizing
//    @Test
//    public void testRebuildThenVerifyNewSizingAcceptsNextInsert() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(1, 2, 2);
//        ht.insert(0, 0);
//
//
//        int preSize = ht.size();
//        int preTombs = ht.tombstones();
//        int preTableSize = ht.tableSize();
//        ht.insert(1, 1);
//        ht.insert(2, 2); // rebuild
//
//        int postSize = ht.size();
//        int postTombs = ht.tombstones();
//        int postTableSize = ht.tableSize();
//
//        assertEquals(preSize + 2, postSize);
//        assertEquals(0, postTombs);
//        assertEquals(preTableSize * 2, postTableSize);
//
//        int sizeBeforeExtra = ht.size();
//        ht.insert(3, 3);
//        assertEquals(sizeBeforeExtra + 1, ht.size());
//        assertEquals(8, ht.tableSize());
//    }
//
//    // =========================
//    // Reflection-based table validation tests 36–45
//    // =========================
//
//    // 36 — No rebuild: straight placements; validate bucket snapshots
//    @Test
//    public void test36_NoRebuild_StraightPlacementsSnapshot() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(2, 4, 4);
//        ht.insert(0, "A");   // bucket 0
//        ht.insert(1, "B");   // bucket 1
//        ht.insert(8, "C");   // bucket 0 (same idx 0)
//        var snap = snapshotTable(ht);
//
//        // Expect bucket 0 has ["0:A", "8:C"]; bucket 1 has ["1:B"]; others empty
//        assertEquals(java.util.List.of("0:A", "8:C"), snap.get(0));
//        assertEquals(java.util.List.of("1:B"),        snap.get(1));
//        assertTrue(snap.get(2).isEmpty());
//        assertTrue(snap.get(3).isEmpty());
//        assertEquals(3, countEntries(ht));
//        assertEquals(0, countTombstonesFromTable(ht));
//    }
//
//    // 37 — No rebuild: remove creates tombstone (null) preserved in snapshot
//    @Test
//    public void test37_NoRebuild_TombstoneVisibleInSnapshot() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(1, 3, 3);
//        ht.insert(0, "A");  // bucket 0: [0:A]
//        ht.insert(3, "B");  // bucket 1: [3:B]
//        assertEquals("A", ht.remove(0)); // bucket 0: [null]
//        var snap = snapshotTable(ht);
//
//        assertEquals(java.util.List.of("null"), snap.get(0)); // tombstone kept
//        assertEquals(java.util.List.of("3:B"),  snap.get(1));
//        assertTrue(snap.get(2).isEmpty());
//        assertEquals(1, countEntries(ht));
//        assertEquals(1, countTombstonesFromTable(ht));
//    }
//
//    // 38 — Load-factor rebuild; verify all items rehashed; no tombstones remain
//    @Test
//    public void test38_Rebuild_LoadFactor_NoTombstonesRemain() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(1, 2, 2);
//        ht.insert(0, 10);
//        ht.insert(1, 20);
//        // Trigger rebuild on third insert
//        ht.insert(2, 30);
//
//        assertEquals(3, ht.size());
//        assertEquals(0, ht.tombstones());
//
//        var snap = snapshotTable(ht);
//        int nonNull = countEntries(ht);
//        int tombs   = countTombstonesFromTable(ht);
//        assertEquals(3, nonNull);
//        assertEquals(0, tombs);
//
//        // capacity=1 per bucket; after rebuild distribution respects capacity
//        for (java.util.List<String> b : snap) {
//            assertTrue(b.size() <= 1);
//        }
//    }
//
//    // 39 — Saturation rebuild (wrap-around probe)
//    @Test
//    public void test39_Rebuild_Saturation_WrapAround() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(1, 3, 3);
//        ht.insert(0, 0);
//        ht.insert(3, 3);
//        ht.remove(0);
//        ht.insert(6, 6);
//
//        //rebuild
//        ht.insert(9, 9);
//
//        assertEquals(3, countEntries(ht));
//        assertEquals(0, countTombstonesFromTable(ht));
//        var snap = snapshotTable(ht);
//        // Post-rebuild: no "null" and each bucket size <= 1 (capacity=1)
//        for (java.util.List<String> b : snap) {
//            for (String s : b) assertNotEquals("null", s);
//            assertTrue(b.size() <= 1);
//        }
//    }
//
//    // 40 — Load-factor rebuild after tombstones exist; ensure tombstones were compacted away
//    @Test
//    public void test40_Rebuild_CleansTombstonesFromBuckets() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(1, 4, 4);
//        ht.insert(0, 0);
//        ht.insert(1, 1);
//        ht.insert(2, 2);
//        ht.remove(1); // create tombstone
//        // Trigger rebuild on next insert ((size+tombs) reaches loadFactor)
//        ht.insert(3, 3);
//
//        assertEquals(0, ht.tombstones());
//        assertEquals(8, ht.tableSize()); // doubled
//
//        var snap = snapshotTable(ht);
//        // No "null" strings should remain post-rebuild
//        for (java.util.List<String> b : snap)
//            for (String s : b) assertNotEquals("null", s);
//    }
//
//    // 41 — No rebuild: replacement updates value in-place; snapshot shows new value, same count
//    @Test
//    public void test41_NoRebuild_ReplacementInPlace() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(2, 4, 4);
//        ht.insert(5, "A");
//        ht.insert(1, "B");
//        assertEquals("A", ht.insert(5, "Z")); // replace value at same entry
//        var snap = snapshotTable(ht);
//
//        boolean found = false;
//        for (java.util.List<String> b : snap) {
//            for (String s : b) {
//                if (s.startsWith("5:")) {
//                    found = true;
//                    assertEquals("5:Z", s);
//                }
//            }
//        }
//        assertTrue(found);
//        assertEquals(2, countEntries(ht));
//        assertEquals(0, countTombstonesFromTable(ht));
//    }
//
//    // 42 — No rebuild: capacity=2, overflow to next bucket; validate exact bucket contents
//    @Test
//    public void test42_NoRebuild_CapacityTwo_OverflowNextBucket() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(2, 4, 2);
//        ht.insert(0, 0);  // bucket 0
//        ht.insert(2, 2);  // bucket 0 (room)
//        ht.insert(4, 4);  // bucket 0 full → next bucket 1
//        var snap = snapshotTable(ht);
//
//        assertEquals(java.util.List.of("0:0", "2:2"), snap.get(0));
//        assertEquals(java.util.List.of("4:4"),        snap.get(1));
//        assertEquals(3, countEntries(ht));
//        assertEquals(0, countTombstonesFromTable(ht));
//    }
//
//    // 43 — No rebuild: clear() resets buckets array; snapshot shows all buckets empty
//    @Test
//    public void test43_NoRebuild_ClearResetsBuckets() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(2, 8, 4);
//        ht.insert(0, 0);
//        ht.insert(1, 1);
//        ht.remove(0); // create tombstone
//        ht.clear();
//
//        var snap = snapshotTable(ht);
//        for (java.util.List<String> b : snap) assertTrue(b.isEmpty());
//        assertEquals(0, ht.size());
//        assertEquals(0, ht.tombstones());
//        assertEquals(4, ht.tableSize()); // table size preserved on clear
//    }
//
//    // 45 — Rebuilds after many inserts; ensure (a) no bucket exceeds capacity and (b) no nulls remain
//    @Test
//    public void test45_Rebuilds_NoBucketExceedsCapacity_PostConditions() {
//        DS4_HashTable<Integer, Integer> ht = new DS4_HashTable<>(2, 4, 2);
//        // Insert enough to trigger at least one rebuild
//        for (int i = 0; i < 10; i++) ht.insert(i, i);
//
//        // Postconditions: no tombstones, and each bucket size <= capacity (2)
//        assertEquals(0, ht.tombstones());
//        var raw = getRawTable(ht);
//        for (ArrayList<?> bucket : raw) {
//            assertTrue("bucket size exceeded capacity", bucket.size() <= 2);
//            for (Object e : bucket) assertNotNull("null found after rebuild", e);
//        }
//        // Count entries from table equals size()
//        assertEquals(ht.size(), countEntries(ht));
//    }
//
//    @Test
//    public void test46_NoRebuild_TombstoneVisibleInSnapshot() {
//        DS4_HashTable<Integer, String> ht = new DS4_HashTable<>(3, 10, 5);
//        ht.insert(0, "A");
//        ht.insert(5, "B");
//        ht.insert(10,"C");
//        ht.insert(1, "D");
//        ht.insert(15, "E");
//        assertEquals("B", ht.remove(5));
//        ht.insert(2, "F");
//        ht.insert(3, "G");
//        assertEquals("G", ht.remove(3));
//        var snap = snapshotTable(ht);
//
//        assertEquals(2,ht.tombstones());
//        assertEquals(5,ht.size());
//        assertEquals(5, ht.tableSize());
//
//        assertEquals("[0:A, null, 10:C]", snap.get(0).toString());
//        assertEquals("[1:D, 15:E]", snap.get(1).toString());
//        assertEquals("[2:F]", snap.get(2).toString());
//        assertEquals("[null]", snap.get(3).toString());
//        assertEquals("[]", snap.get(4).toString());
//
//    }
//}

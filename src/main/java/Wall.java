package main.java;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Wall implements Structure {

    private List<Block> blocks;

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream()
                .filter(block -> {
                    String blockColor = block.getColor();
                    return blockColor != null && blockColor.equals(color) ||
                            (block instanceof CompositeBlock && findByColor((CompositeBlock) block, color).isPresent());
                })
                .findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
            return blocks.stream()
                    .flatMap(block -> {
                        String blockMaterial = block.getMaterial();
                        if (blockMaterial != null && blockMaterial.equals(material)) {
                            return Stream.of(block);
                        } else if (block instanceof CompositeBlock) {
                            return findByMaterial((CompositeBlock) block, material).stream();
                        } else {
                            return Stream.empty();
                        }
                    })
                    .toList();
        }

    @Override
    public int count () {
        int count = 0;
        for (Block block : blocks) {
            count += countBlocks(block);
        }
        return count;
    }

    private Optional<Block> findByColor(CompositeBlock compositeBlock, String color) {
        return compositeBlock.getBlocks().stream()
                .filter(block -> {
                    String blockColor = block.getColor();
                    return blockColor != null && blockColor.equals(color) ||
                            (block instanceof CompositeBlock && findByColor((CompositeBlock) block, color).isPresent());
                })
                .findFirst();
    }

    private List<Block> findByMaterial(CompositeBlock compositeBlock, String material) {
        return compositeBlock.getBlocks().stream()
                .flatMap(block -> {
                    String blockMaterial = block.getMaterial();
                    if (blockMaterial != null && blockMaterial.equals(material)) {
                        return Stream.of(block);
                    } else if (block instanceof CompositeBlock) {
                        return findByMaterial((CompositeBlock) block, material).stream();
                    } else {
                        return Stream.empty();
                    }
                })
                .toList();
    }

    private int countBlocks (Block block){
        int count = 1;
        if (block instanceof CompositeBlock) {
            for (Block b : ((CompositeBlock) block).getBlocks()) {
                count += countBlocks(b);
            }
        } else {
            return count;
        }
        return count;
    }

}
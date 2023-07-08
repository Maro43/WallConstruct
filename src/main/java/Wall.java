package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Wall implements Structure {

    private List<Block> blocks;

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findByColor(blocks, color);
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream()
                .flatMap(block -> findByMaterial(block, material).stream())
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

    private Optional<Block> findByColor(List <Block> blocks, String color) {
        for (Block block : blocks) {
            String blockColor = block.getColor();
            if (blockColor != null && blockColor.equals(color)) {
                return Optional.of(block);
            } else if (block instanceof CompositeBlock) {
                Optional<Block> result = findByColor(((CompositeBlock) block).getBlocks(), color);
                if (result.isPresent()) {
                    return result;
                }
            }
        }
        return Optional.empty();
    }

    private List<Block> findByMaterial(Block block, String material) {
        List<Block> result = new ArrayList<>();

        if (block.getMaterial() != null && block.getMaterial().equals(material)) {
            result.add(block);
        }

        if (block instanceof CompositeBlock) {
            CompositeBlock compositeBlock = (CompositeBlock) block;
            for (Block subBlock : compositeBlock.getBlocks()) {
                result.addAll(findByMaterial(subBlock, material));
            }
        }
        return result;
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
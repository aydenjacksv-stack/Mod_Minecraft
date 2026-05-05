package com.fpsboost;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class FpsBoostConfig {

    public static FpsBoostConfig INSTANCE = new FpsBoostConfig();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH =
        FabricLoader.getInstance().getConfigDir().resolve("fpsboost.json");

    public boolean entityCulling = true;
    public int maxParticles = 1000;
    public boolean fogOptimizer = true;
    public int maxChunkUpdatesPerTick = 3;

    public static void load() {
        if (Files.exists(CONFIG_PATH)) {
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
                INSTANCE = GSON.fromJson(reader, FpsBoostConfig.class);
            } catch (IOException e) {
                FpsBoostMod.LOGGER.error("Failed to load config, using defaults", e);
                INSTANCE = new FpsBoostConfig();
            }
        }
        save();
    }

    public static void save() {
        try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
            GSON.toJson(INSTANCE, writer);
        } catch (IOException e) {
            FpsBoostMod.LOGGER.error("Failed to save config", e);
        }
    }
}

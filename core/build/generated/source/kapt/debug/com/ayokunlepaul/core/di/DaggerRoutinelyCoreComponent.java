// Generated by Dagger (https://dagger.dev).
package com.ayokunlepaul.core.di;

import com.ayokunlepaul.core.di.modules.ContextModule;
import com.ayokunlepaul.core.di.modules.ContextModule_ProvideContextFactory;
import com.ayokunlepaul.core.utils.ExecutionThreadImpl;
import com.ayokunlepaul.domain.local.DomainRoutineLocal;
import com.ayokunlepaul.domain.utils.ExecutionThread;
import com.ayokunlepaul.local.di.RoutinelyDatabaseModule;
import com.ayokunlepaul.local.di.RoutinelyDatabaseModule_ProvideRoutineDAO$local_debugFactory;
import com.ayokunlepaul.local.di.RoutinelyDatabaseModule_ProvideRoutinelyDatabase$local_debugFactory;
import com.ayokunlepaul.local.impl.RepositoryRoutineLocalImpl;
import com.ayokunlepaul.local.room.RoutinelyDatabase;
import com.ayokunlepaul.local.room.daos.RoutineDAO;
import com.ayokunlepaul.repository.impl.DomainRoutineLocalImpl;
import com.ayokunlepaul.repository.local.RepositoryRoutineLocal;
import dagger.internal.Preconditions;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerRoutinelyCoreComponent implements RoutinelyCoreComponent {
  private final ContextModule contextModule;

  private final RoutinelyDatabaseModule routinelyDatabaseModule;

  private DaggerRoutinelyCoreComponent(RoutinelyDatabaseModule routinelyDatabaseModuleParam,
      ContextModule contextModuleParam) {
    this.contextModule = contextModuleParam;
    this.routinelyDatabaseModule = routinelyDatabaseModuleParam;
  }

  public static RoutinelyCoreComponent.Builder builder() {
    return new Builder();
  }

  private RoutinelyDatabase getRoutinelyDatabase() {
    return RoutinelyDatabaseModule_ProvideRoutinelyDatabase$local_debugFactory.provideRoutinelyDatabase$local_debug(routinelyDatabaseModule, ContextModule_ProvideContextFactory.provideContext(contextModule));}

  private RoutineDAO getRoutineDAO() {
    return RoutinelyDatabaseModule_ProvideRoutineDAO$local_debugFactory.provideRoutineDAO$local_debug(routinelyDatabaseModule, getRoutinelyDatabase());}

  private RepositoryRoutineLocalImpl getRepositoryRoutineLocalImpl() {
    return new RepositoryRoutineLocalImpl(getRoutineDAO());}

  private DomainRoutineLocalImpl getDomainRoutineLocalImpl() {
    return new DomainRoutineLocalImpl(getRepositoryRoutineLocalImpl());}

  @Override
  public ExecutionThread provideExecutionThread() {
    return new ExecutionThreadImpl();}

  @Override
  public DomainRoutineLocal provideDomainRoutineLocal() {
    return getDomainRoutineLocalImpl();}

  @Override
  public RepositoryRoutineLocal provideRepositoryRoutineLocal() {
    return getRepositoryRoutineLocalImpl();}

  private static final class Builder implements RoutinelyCoreComponent.Builder {
    private ContextModule contextModule;

    @Override
    public Builder provideContextModule(ContextModule contextModule) {
      this.contextModule = Preconditions.checkNotNull(contextModule);
      return this;
    }

    @Override
    public RoutinelyCoreComponent buildCoreComponent() {
      Preconditions.checkBuilderRequirement(contextModule, ContextModule.class);
      return new DaggerRoutinelyCoreComponent(new RoutinelyDatabaseModule(), contextModule);
    }
  }
}

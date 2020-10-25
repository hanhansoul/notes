
  const ret = [100, 100, 20, 0, 0, 0];
  // range(0, 5).pipe(
  //     concatMap(offset => {
  //         console.log('offset: ' + offset);
  //         return of(ret[offset]);
  //     }),
  //     takeWhile((count: number) => count > 0)
  // ).subscribe(val => {
  //     console.log(val);
  // });

  let notifier = new Subject();
  range(0, 5).pipe(
      takeUntil(notifier),
      concatMap(offset => {
          console.log('offset: ' + offset);
          return of(ret[offset]);
      })
  ).subscribe(val => {
      console.log(val);
      if (val < 100) {
          notifier.next();
          notifier.complete();
      }
  });

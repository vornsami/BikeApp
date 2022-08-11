
import React from 'react'
import Grid from '@material-ui/core/Grid'


const BikePath = ({setSort, sortBy}) => {
  const sortByElement = <b>↓</b>

  return <Grid container item spacing={2}>
      <Grid item xs>
        Departure station id {sortBy === 'Departure station id' && sortByElement}
      </Grid>
      <Grid item xs>
        Departure station name {sortBy === 'Departure station name' && sortByElement}
      </Grid>
      <Grid item xs>
        Departure time {sortBy === 'Departure' && sortByElement}
      </Grid>
      <Grid item xs>
        Return station id {sortBy === 'Return station id' && sortByElement}
      </Grid>
      <Grid item xs>
        Return station name {sortBy === 'Return station name' && sortByElement}
      </Grid>
      <Grid item xs>
        Return time {sortBy === 'Return' && sortByElement}
      </Grid>
      <Grid item xs>
        Distance {sortBy === 'Distance' && sortByElement}
      </Grid>
      <Grid item xs>
        Duration {sortBy === 'Duration' && sortByElement}
      </Grid>
    </Grid>
}

export default BikePath